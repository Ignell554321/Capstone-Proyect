import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-compra',
  templateUrl: './compra.component.html',
  styleUrls: ['./compra.component.css']
})
export class CompraComponent implements OnInit {

  public registro:Boolean= false;

  constructor() { }

  public nuevaCompra(){
    this.registro=true;
  }

  public cancelar(){
    this.registro=false;
  }


  ngOnInit(): void {
  }

}
