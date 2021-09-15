import { Injectable } from '@angular/core';
import { CompraModule } from '../models/compra.module';
import { HttpClient, HttpHeaders} from '@angular/common/http'
import { DetalleCompraModule } from '../models/detalle-compra.module';
import { Observable } from 'rxjs';
import { CompraComponent } from '../pages/compra/compra.component';

@Injectable({
  providedIn: 'root'
})
export class CompraService {

  readonly URL_API='http://localhost:8081/compra/';

  public listaCompras:CompraModule[]=[]
  public detallesCompra:DetalleCompraModule[]=[]

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json; charset=ISO-8859-15', 'Accept': 'application/json'})
  };

  constructor(private httpClient:HttpClient) { }


  public listarDetalles(){

    return this.httpClient.get(`${this.URL_API}/listarDetalles`);

  }

  public agregarDetalle(detalle:DetalleCompraModule): Observable<any>{
    return this.httpClient.post(`${this.URL_API}/addDetalle`,detalle);
  }

  public guardar(compra:CompraComponent): Observable<any>{
    return this.httpClient.post(this.URL_API,compra);
  }

  public limpiarDetalles(){
    return this.httpClient.get(`${this.URL_API}/limpiarDetalles`);
  }
  
}
