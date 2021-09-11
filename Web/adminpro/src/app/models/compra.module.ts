import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

@NgModule({
  declarations: [],
  imports: [
    CommonModule
  ]
})
export class CompraModule { 


  public id:number
  public proveedor:String
  public fecha:Date
  public numComprobante:String
  public detalleCompras:String[] 

  constructor(){
    this.id=0
    this.proveedor=''
    this.fecha=new Date
    this.numComprobante=''
    this.detalleCompras=[]
  }


}
