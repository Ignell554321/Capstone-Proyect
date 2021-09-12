import { Component, OnInit } from '@angular/core';

import { BsModalService } from 'ngx-bootstrap/modal';
import { BsModalRef } from 'ngx-bootstrap/modal/bs-modal-ref.service';

import { ModalProductoComponent } from '../modal-producto/modal-producto.component';
import { ProductoModule } from 'src/app/models/producto.module';
import { ProductoService } from 'src/app/service/producto.service';

import Swal from 'sweetalert2';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-compra',
  templateUrl: './compra.component.html',
  styleUrls: ['./compra.component.css']
})
export class CompraComponent implements OnInit {

  public registro:Boolean= false;
  public modalRef: BsModalRef | undefined;

  constructor(private modalService: BsModalService) { }

  public nuevaCompra(){
    this.registro=true;
  }

  public cancelar(){
    this.registro=false;
  }

  public cargarModalProducto(){

    this.modalRef = this.modalService.show(ModalProductoComponent)

      this.modalRef.content.response.subscribe((value: ProductoModule) => {
        console.log(value)
      }
       
    );

  }

  ngOnInit(): void {
  }

}
