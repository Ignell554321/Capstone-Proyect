import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router'; 

@Component({
  selector: 'app-ingresar',
  templateUrl: './ingresar.component.html',
  styleUrls: ['./ingresar.component.css']
})
export class IngresarComponent implements OnInit {
  public activo:Boolean= true;

  constructor(private routes:Router) { }

  public nuevaCompra(){
    this.activo=false;
    console.log(this.activo)
  }

  public cancelar(){
    this.routes.navigate(['/dashboard/compra']);
  }

  ngOnInit(): void {
  }

}
