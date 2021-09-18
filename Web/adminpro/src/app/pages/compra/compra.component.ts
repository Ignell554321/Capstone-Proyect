import { Component, OnInit } from '@angular/core';

import { BsModalService } from 'ngx-bootstrap/modal';
import { BsModalRef } from 'ngx-bootstrap/modal/bs-modal-ref.service';
import { BsLocaleService, DatepickerDateCustomClasses } from 'ngx-bootstrap/datepicker';

import { ModalProductoComponent } from '../modal-producto/modal-producto.component';
import Swal from 'sweetalert2';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ProductoModule } from 'src/app/models/producto.module';
import { CompraService } from 'src/app/service/compra.service';
import { DetalleCompraModule } from 'src/app/models/detalle-compra.module';
import { CompraModule } from 'src/app/models/compra.module';
import * as moment from 'moment';

@Component({
  selector: 'app-compra',
  templateUrl: './compra.component.html',
  styleUrls: ['./compra.component.css']
})
export class CompraComponent implements OnInit {

  public registro:Boolean= false
  public modalRef: BsModalRef | undefined
  public frmCompra: FormGroup
  public DetalleCompra:FormGroup

  //----- PAGINADO -----------------

  public prev:number=0
  public last:number=0
  public next:number=0
  public current:number=0
  public page:number=1
  public tamanio:number=10
  public paginas:number[]=[]
  public tamaniosPaginado:number[]=[10,30,50]
  public totalRegistros:number=0
  public dateCustomClasses: DatepickerDateCustomClasses[] | undefined
  //----- PAGINADO -----------------


  constructor(private formBuilder: FormBuilder,private modalService: BsModalService, public service:CompraService,private bsLocaleService: BsLocaleService) { 

      this.bsLocaleService.use('es')
      const now = new Date()
      this.dateCustomClasses = [ { date: now, classes: ['bg-warning'] } ]
      

    this.frmCompra= this.formBuilder.group({
      id: 0,
      proveedor: ['',Validators.required],
      fechaRegistro: [new Date],
      fechaPago: ['',Validators.required],
      estado: ['CREADO'],
      montoTotal: [0,Validators.required],
      numComprobante: ['',Validators.required],
      detalleCompras: this.formBuilder.array([])
    }) 

    this.DetalleCompra=this.formBuilder.group({
      id:0,
      producto_id:[0,Validators.required],
      nombreProducto:['',Validators.required],
      cantidad:[0,Validators.required],
      precio:[0,Validators.required],
      subTotal:[0,Validators.required]

    })

    

  }

  get detalleCompras(){
    return this.frmCompra.get('detalleCompras') as FormArray
  }


  public editar(compra:CompraModule){

    this.service.findById(compra.id).subscribe(res=>{

      var entity=res as CompraModule

    //  var momentObj = moment(entity.fechaPago);
     // var momentString = momentObj.format('YYYY-MM-DD'); 
     
      this.registro=true;
      this.frmCompra.patchValue(entity)

     // this.frmCompra.get('fechaPago')?.setValue(momentString)
      this.service.detallesCompra= entity.detalleCompras
      console.log(entity.detalleCompras)

      
 
    })

  }

  public editarDetalle(detalle:DetalleCompraModule){

   this.DetalleCompra.patchValue(detalle)

  }

 /* private removerDetalle(indice:number){
    this.filtros.removeAt(indice)
  }*/

  public eliminarDetalle(detalle:DetalleCompraModule){

    console.log(detalle)

    this.service.eliminarDetalle(detalle).subscribe(res=>{
      this.listarDetalles()
    })
  }

  public listarCompras(){

    this.service.listar().subscribe(res=>{

      console.log(res)
      this.service.listaCompras=res as CompraModule[]

    })

  }

  public guardarCompra(){

    var fechaPago=this.frmCompra.get('fechaPago')?.value
    fechaPago = moment(fechaPago).format("YYYY/MM/DD")
    const fecha=new Date(fechaPago)
    this.frmCompra.get('fechaPago')?.setValue(fecha)

    if(this.frmCompra.valid){

      
      this.service.guardar(this.frmCompra.value).subscribe(res=>{

        console.log(res)

        Swal.fire('Exito', 'Compra Registrada Correctamente!', 'success').then((result)=>{ if (result.isConfirmed) {

          this.obtenerPaginado(this.page)
           this.registro=false  
           //this.limpiarFormulario()
         
         }})

      })

     

    }else{
      Swal.fire('Error', 'Debes completar el formulario. Verifique por favor.', 'error')
    }

  }

  public listarDetalles()
  {

    //var montoTotal=this.frmCompra.get('montoTotal')?.value


   this.service.listarDetalles().subscribe(res=>{

    this.service.detallesCompra =res as DetalleCompraModule[]
    this.detalleCompras.clear()
    var montoTotal=0

    for (let objeto of this.service.detallesCompra) {

      const filtroFormGroup = this.formBuilder.group({
        id: 0
      , nombreProducto: objeto.nombreProducto
      , producto_id: objeto.producto_id
      , cantidad: objeto.cantidad
      , precio: objeto.precio
      , subTotal: objeto.subTotal
    })

    montoTotal=montoTotal+objeto.subTotal
    
    

     this.detalleCompras.push(filtroFormGroup)
    }

    this.frmCompra.get('montoTotal')?.setValue(Number(montoTotal))

   });
  }
  
  public onChangeMostrar(tamanio:any) {
    
    this.page=1
    this.tamanio= Number(tamanio)
    this.obtenerPaginado(this.page)

}

  public obtenerPaginadoInicial(pagina:number){

    this.page=pagina
    this.next=pagina+1
    this.prev=pagina-1
    this.paginas=[]
    this.current=pagina

    $('#loading').fadeIn('slow')

      this.service.listarPaginas(pagina-1,this.tamanio).subscribe(res=>{
        console.log(res)
        this.last=res.totalPages as number
        this.service.listaCompras=res.content as  CompraModule[];
        this.totalRegistros=res.totalElements as number
    
          for (let i=0; i < this.last; i++){
            this.paginas.push(i+1)
          }
          $('#loading').fadeOut('slow')
        });

  }

  public obtenerPaginado(pagina:number){

    if(pagina-1<0 || pagina>this.last){
     return
    }

    this.page=pagina
    this.next=pagina+1
    this.prev=pagina-1
    this.paginas=[]
    this.current=pagina
    $('#loading').fadeIn('slow')
    
    this.service.listarPaginas(pagina-1,this.tamanio).subscribe(res=>{
    console.log(res)
    this.last=res.totalPages as number
    this.service.listaCompras=res.content as  CompraModule[];
    this.totalRegistros=res.totalElements as number

      for (let i=0; i < this.last; i++){
        this.paginas.push(i+1)
      }
      $('#loading').fadeOut('slow')
    });


  }

  public agregarDetalle(){

    
    if(this.DetalleCompra.valid){

      this.service.agregarDetalle(this.DetalleCompra.value).subscribe(res =>{

        console.log(res)
        this.limpiarFormularioDetalles()
        this.listarDetalles()

      })
      
    }

  }

  private limpiarFormularioCompra(){
    
    this.frmCompra.reset(
      {
        id: 0,
        proveedor: '',
        fechaRegistro: new Date,
        fechaPago: '',
        estado: 'CREADO',
        montoTotal:0,
        numComprobante:'',
        detalleCompras:[]
      }
    )

  }

  private limpiarFormularioDetalles(){
    
    this.DetalleCompra.reset(
      {
        id: 0,
        producto_id:0,
        nombreProducto: '',
        cantidad: 0,
        precio: 0,
        subTotal: 0
      }
    )

  }

  public nuevaCompra(){
    this.registro=true;
    this.limpiarDetalles()
    this.limpiarFormularioCompra()

  }

  public cancelar(){
    this.registro=false;
  }

  public limpiarDetalles(){

    this.service.limpiarDetalles().subscribe(res=>{

      this.service.detallesCompra=[]

      console.log(res)

    })


  }

  public cargarModalProducto(){

    this.modalRef = this.modalService.show(ModalProductoComponent)

      this.modalRef.content.response.subscribe((producto: ProductoModule) => {
        this.DetalleCompra.get('nombreProducto')?.setValue(producto.nombre)
        this.DetalleCompra.get('producto_id')?.setValue(producto.id)
        console.log(producto)
        console.log(this.DetalleCompra.value)
      }
       
    );

  }

  ngOnInit(): void {

    this.limpiarFormularioDetalles()
    this.obtenerPaginadoInicial(this.page)
  }

}
