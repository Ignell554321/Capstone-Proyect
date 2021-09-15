import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DetalleCompraModule } from './detalle-compra.module';

@NgModule({
  declarations: [],
  imports: [
    CommonModule
  ]
})
export class CompraModule { 


  public id:number
  public proveedor:String
  public fechaRegistro:Date
  public fechaPago:Date | undefined
  public numComprobante:String
  public estado:String
  public montoTotal:number
  public detalleCompras:DetalleCompraModule[] 

  constructor(){
    this.id=0
    this.proveedor=''
    this.fechaRegistro=new Date
    this.montoTotal=0
    this.numComprobante=''
    this.detalleCompras=[]
    this.estado=''
  }


}
