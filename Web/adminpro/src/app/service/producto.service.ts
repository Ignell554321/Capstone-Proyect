import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders} from '@angular/common/http'
import { ProductoModule } from '../models/producto.module';

@Injectable({
  providedIn: 'root'
})
export class ProductoService {

  readonly URL_API='http://localhost:8081/api/productos/';

  public listaProductos:ProductoModule[]=[]

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json; charset=ISO-8859-15', 'Accept': 'application/json'})
  };

  constructor(private httpClient:HttpClient) { }

  public listarProductos(){

      return this.httpClient.get(this.URL_API);
  }

}
