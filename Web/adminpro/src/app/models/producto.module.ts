import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';


@NgModule({
  declarations: [],
  imports: [
    CommonModule
  ]
})

export class ProductoModule { 

  public id:number
  public nombre:String
  public descripcion:String
  public costo:number
  
  constructor(){
    this.id=0
    this.nombre=''
    this.descripcion=''
    this.costo=0
  
  }

}
