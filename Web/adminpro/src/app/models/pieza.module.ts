import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ProductoModule } from './producto.module';



@NgModule({
  declarations: [],
  imports: [
    CommonModule
  ]
})
export class PiezaModule {

  public id:number
  public metraje:number
  public color:String
  public ubicacion:String
  public fecha:Date
  public producto:ProductoModule

  constructor(){

    this.id=0
    this.metraje=0
    this.color=''
    this.ubicacion=''
    this.fecha=new Date
    this.producto=new ProductoModule

  }

 }
