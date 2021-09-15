import { Component, OnInit } from '@angular/core';

import { BsModalService } from 'ngx-bootstrap/modal';
import { BsModalRef } from 'ngx-bootstrap/modal/bs-modal-ref.service';

import { ModalProductoComponent } from '../modal-producto/modal-producto.component';
import Swal from 'sweetalert2';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ProductoModule } from 'src/app/models/producto.module';
import { CompraService } from 'src/app/service/compra.service';
import { DetalleCompraModule } from 'src/app/models/detalle-compra.module';

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

  constructor(private formBuilder: FormBuilder,private modalService: BsModalService, public service:CompraService) { 

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
      nombreProducto:['',Validators.required],
      cantidad:[0,Validators.required],
      precio:[0,Validators.required],
      subTotal:[0,Validators.required]

    })

  }

  get detalleCompras(){
    return this.frmCompra.get('detalleCompras') as FormArray
  }


 /* private removerDetalle(indice:number){
    this.filtros.removeAt(indice)
  }*/

  public guardarCompra(){
    
    console.log(this.frmCompra.value)
    if(this.frmCompra.valid){

      this.service.guardar(this.frmCompra.value).subscribe(res=>{

        console.log(res)

        Swal.fire('Exito', 'Compra Registrada Correctamente!', 'success').then((result)=>{ if (result.isConfirmed) {

          //this.obtenerPaginado(this.page)
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
   this.service.listarDetalles().subscribe(res=>{

    this.service.detallesCompra =res as DetalleCompraModule[]
    for (let objeto of this.service.detallesCompra) {

      const filtroFormGroup = this.formBuilder.group({
        id: 0
      , nombreProducto: objeto.nombreProducto
      , cantidad: objeto.cantidad
      , precio: objeto.precio
      , subTotal: objeto.subTotal
    })

     this.detalleCompras.push(filtroFormGroup)
    }

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
        console.log(producto)
      }
       
    );

  }

  ngOnInit(): void {

    this.limpiarFormularioDetalles()
  }

}
