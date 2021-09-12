import { Component, OnInit } from '@angular/core';
import { PiezaModule } from 'src/app/models/pieza.module';
import { PiezaService } from 'src/app/service/pieza.service';
import Swal from 'sweetalert2';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';


import { BsModalService } from 'ngx-bootstrap/modal';
import { BsModalRef } from 'ngx-bootstrap/modal/bs-modal-ref.service';
import { ModalProductoComponent } from '../modal-producto/modal-producto.component';
import { ProductoModule } from 'src/app/models/producto.module';
import * as $ from 'jquery';
import { ProductoService } from 'src/app/service/producto.service';

@Component({
  selector: 'app-pieza',
  templateUrl: './pieza.component.html',
  styleUrls: ['./pieza.component.css']
})
export class PiezaComponent implements OnInit {

  constructor(public service:PiezaService,private formBuilder: FormBuilder,private modalService: BsModalService, public serviceProductos:ProductoService) { 

    this.frmPieza= this.formBuilder.group({
      id: [''],
      metraje: [0,Validators.required],
      color: ['',Validators.required],
      ubicacion: ['',Validators.required],
      fecha: [new Date,Validators.required],
      producto: this.formBuilder.group({

        id: [''],
        nombre: ['',Validators.required],
        descripcion: ['',Validators.required],
        costo: [0,Validators.required]
      })
    }) 

  }

  
  public registro:boolean=false; 
  public frmPieza: FormGroup;
  public modalRef: BsModalRef | undefined;
  public producto:ProductoModule | undefined;

  public prev:number=0
  public last:number=0
  public next:number=0
  public current:number=0
  public page:number=1
  public tamanio:number=10
  public paginas:number[]=[]
  public tamaniosPaginado:number[]=[10,30,50]
  public totalRegistros:number=0
  public isDisabled:boolean=true

  public filtroProducto:boolean=false
  public filtroProductoId:any=0


  ngOnInit(): void {
    
    this.obtenerPaginadoInicial(this.page)
    this.configuracionInicial()

  }


  public configuracionInicial(){

    this.serviceProductos.listarProductos().subscribe(res=>{
      this.serviceProductos.listaProductos=res as  ProductoModule[];

    });

  }

  public onChangeProducto(){

    
    if(this.filtroProductoId!=0){

      var pagina=1
      this.page=pagina
      this.next=pagina+1
      this.prev=pagina-1
      this.paginas=[]
      this.current=pagina
      this.filtroProducto=true
      $('#loading').fadeIn('slow')
  
      this.service.listarPaginasFiltroProducto(this.filtroProductoId,pagina-1,this.tamanio).subscribe(res=>{

        this.last=res.totalPages as number
        this.service.piezas=res.content as  PiezaModule[]
        this.totalRegistros=res.totalElements as number
  
        for (let i=0; i < this.last; i++){
          this.paginas.push(i+1)
        }
        $('#loading').fadeOut('slow')
  
      });
    }else{

      this.filtroProducto=false
      this.obtenerPaginadoInicial(1)
    }

 

  }

  public onChangeMostrar(tamanio:any) {

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
        this.service.piezas=res.content as  PiezaModule[];
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
    
    if(!this.filtroProducto){

      console.log('paginado general')
        
        this.service.listarPaginas(pagina-1,this.tamanio).subscribe(res=>{
        console.log(res)
        this.last=res.totalPages as number
        this.service.piezas=res.content as  PiezaModule[];
        this.totalRegistros=res.totalElements as number

          for (let i=0; i < this.last; i++){
            this.paginas.push(i+1)
          }
          $('#loading').fadeOut('slow')
        });

    }else{

      console.log('paginado por filtro producto')
      this.service.listarPaginasFiltroProducto(this.filtroProductoId,pagina-1,this.tamanio).subscribe(res=>{

        this.last=res.totalPages as number
        this.service.piezas=res.content as  PiezaModule[]
        this.totalRegistros=res.totalElements as number

        for (let i=0; i < this.last; i++){
          this.paginas.push(i+1)
        }
        $('#loading').fadeOut('slow')

      });

  }

  }



  public listarPiezas(){

    this.service.listarPiezas().subscribe(res=>{
      this.service.piezas=res as  PiezaModule[];
      console.log(res);
    });

  }

  public editarPieza(pieza:PiezaModule){

    this.service.buscarPieza(pieza.id).subscribe(res=>{
      console.log(res)
      this.registro=true
      this.frmPieza.patchValue(res)
    })

  }

  public cargarModalProducto(){


    this.modalRef = this.modalService.show(ModalProductoComponent,  {
      initialState: {
        piezaComponent:this
      }})

      this.modalRef.content.response.subscribe((producto: ProductoModule) => {
        this.frmPieza.get('producto')?.setValue(producto)
      }
       
    );


  }

  public nuevaPieza(){
    this.registro=true
    this.limpiarFormulario()
  }


  public buscar(){
    
  }

  public cancelar(){
    this.registro=false
  }

  public guardarPieza(){

    if(this.frmPieza.valid){

      this.service.ingresarPieza(this.frmPieza.value).subscribe(res=>{

        
          Swal.fire('Exito', 'Producto Registrado Correctamente!', 'success').then((result)=>{ if (result.isConfirmed) {

            //this.frmPieza.reset()
            this.obtenerPaginado(this.page)
            this.registro=false  
            this.limpiarFormulario()
          
          }})
          
      })

    }else{
      Swal.fire('Error', 'Debes completar el formulario. Verifique por favor.', 'error')
    }
    

  }

  private limpiarFormulario(){
    
    this.frmPieza.reset(
      {
        id: '',
        metraje: 0,
        color: '',
        ubicacion: '',
        fecha: new Date,
        producto:{
          id:'',
          nombre:'',
          descripcion:'',
          costo:0

        }
      }
    )

  }

  public eliminarPieza(pieza:PiezaModule){

    Swal.fire({
      title: 'Deseas eliminar esta pieza?',
      text: "No podras volver a visualizar este registro",
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Si, eliminalo'
    }).then((result) => {
      if (result.isConfirmed) {
        Swal.fire(
          'Eliminado!',
          'La pieza se ha eliminado correctamente.',
          'success'
        )
      }
    })

  }


}
