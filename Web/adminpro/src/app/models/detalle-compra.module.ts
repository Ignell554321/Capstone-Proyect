import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PiezaModule } from './pieza.module';



@NgModule({
  declarations: [],
  imports: [
    CommonModule
  ]
})
export class DetalleCompraModule { 

 
    public id:number;
    public producto_id:number;
    public nombreProducto:String;
    public cantidad:number;
    public subTotal:number;
    public precio:number;

    constructor(){

      this.id=0
      this.producto_id=0
      this.nombreProducto=''
      this.cantidad=0
      this.subTotal=0
      this.precio=0

    }

}
