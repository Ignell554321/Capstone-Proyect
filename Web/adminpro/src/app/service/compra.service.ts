import { Injectable } from '@angular/core';
import { CompraModule } from '../models/compra.module';
import { HttpClient, HttpHeaders, HttpParams} from '@angular/common/http'
import { DetalleCompraModule } from '../models/detalle-compra.module';
import { Observable } from 'rxjs';
import { CompraComponent } from '../pages/compra/compra.component';

@Injectable({
  providedIn: 'root'
})
export class CompraService {

  readonly URL_API='http://localhost:8081/compra';

  public listaCompras:CompraModule[]=[]
  public detallesCompra:DetalleCompraModule[]=[]

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json; charset=ISO-8859-15', 'Accept': 'application/json'})
  };

  constructor(private httpClient:HttpClient) { }


  public listarDetalles(){

    return this.httpClient.get(`${this.URL_API}/listarDetalles`);

  }

  public listar(): Observable<any>{

    return this.httpClient.get(this.URL_API);

  }

  
  public findById(id:number): Observable<any>{

    return this.httpClient.get(this.URL_API+"/"+id);

  }

  public listarPaginas(pagina: number, tamaño: number): Observable<any>{
    const params = new HttpParams()
    .set('page', pagina)
    .set('size', tamaño);
    return this.httpClient.get<any>(`${this.URL_API}/pagina`, {params: params});
  }

  public eliminarDetalle(detalle:DetalleCompraModule): Observable<any>{
    return this.httpClient.post(`${this.URL_API}/eliminarDetalle`,detalle);
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
