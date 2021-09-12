import { Component, EventEmitter, OnInit,Output } from '@angular/core';
import { BsModalRef } from 'ngx-bootstrap/modal';
import { ProductoModule } from 'src/app/models/producto.module';
import { ProductoService } from 'src/app/service/producto.service';
import { PiezaComponent } from '../pieza/pieza.component';

@Component({
  selector: 'app-modal-producto',
  templateUrl: './modal-producto.component.html',
  styleUrls: ['./modal-producto.component.css']
})
export class ModalProductoComponent implements OnInit {

  @Output() response = new EventEmitter();

  constructor(public modalRef: BsModalRef, public service:ProductoService) { }

  public piezaComponent!: PiezaComponent;

  ngOnInit(): void {
    this.listarProductos()
  }

  seleccionarProducto(producto:ProductoModule){
    
   // this.piezaComponent.frmPieza.get('producto')?.setValue(producto)
    this.response.emit(producto)
    this.modalRef.hide()
  }

  seleccionarProductoDoubleClick(producto:ProductoModule){
    
    //this.piezaComponent.frmPieza.get('producto')?.setValue(producto)
    this.response.emit(producto)
    this.modalRef.hide();
    
  }

  public listarProductos(){

    this.service.listarProductos().subscribe(res=>{
      this.service.listaProductos=res as  ProductoModule[];
      console.log(res);
    });

  }

}
