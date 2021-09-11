import { HttpClient,HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { PiezaModule } from '../models/pieza.module';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PiezaService {

  readonly URL_API='http://localhost:8081/api/pieza/';

  public piezas:PiezaModule[]=[]

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json; charset=ISO-8859-15', 'Accept': 'application/json'})
  };

  constructor(private httpClient:HttpClient) { }

  public listarPiezas(){

      return this.httpClient.get(this.URL_API);
  }

  public listarPaginas(pagina: number, tamaño: number): Observable<any>{
    const params = new HttpParams()
    .set('page', pagina)
    .set('size', tamaño);
    return this.httpClient.get<any>(`${this.URL_API}/pagina`, {params: params});
  }

  public listarPaginasFiltroProducto(id:number,pagina: number, tamaño: number): Observable<any>{
    const params = new HttpParams()
    .set('page', pagina)
    .set('size', tamaño);
    return this.httpClient.get<any>(`${this.URL_API}/producto/${id}`, {params: params});
  }

  public listarPaginasFiltroColor(color:String,pagina: number, tamaño: number): Observable<any>{
    const params = new HttpParams()
    .set('page', pagina)
    .set('size', tamaño);
    return this.httpClient.get<any>(`${this.URL_API}/color/${color}`, {params: params});
  }

  public paginado(pagina:number, tamaño:number){

    return this.httpClient.get(this.URL_API+"/pagina/?page="+pagina+"&size="+tamaño);
}

  public buscarPieza(id:number){
    return this.httpClient.get(this.URL_API+"/"+id);
  }

  eliminarPieza(idProducto:number){
    return this.httpClient.get(this.URL_API +"Eliminar"+ `${idProducto}`);
  }

  public ingresarPieza(pieza:PiezaModule){
    return this.httpClient.post(this.URL_API,pieza);
  }
}
