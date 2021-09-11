import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-ingresar-pieza',
  templateUrl: './ingresar-pieza.component.html',
  styleUrls: ['./ingresar-pieza.component.css']
})
export class IngresarPiezaComponent implements OnInit {

  constructor(private rutaActiva: ActivatedRoute) { }

  ngOnInit(): void {


  }

}
