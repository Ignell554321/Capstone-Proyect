import { RouterModule, Routes } from "@angular/router";
import { NgModule } from "@angular/core";

import { PagesComponent } from "./pages.component";
import { DashboardComponent } from "./dashboard/dashboard.component";
import { ProgressComponent } from "./progress/progress.component";
import { Grafica1Component } from "./grafica1/grafica1.component";
import { CompraComponent } from "./compra/compra.component";
import { IngresarComponent } from './compra/ingresar/ingresar.component';

import { PiezaComponent } from "./pieza/pieza.component";
import { IngresarPiezaComponent } from "./pieza/ingresar-pieza/ingresar-pieza.component";

const routes:Routes=[
    {
        path:'dashboard', 
        component: PagesComponent,
        children:[
          {path:'', component: DashboardComponent},
          {path:'progress', component: ProgressComponent},
          {path:'grafica1', component: Grafica1Component},
          {path:'compra', component: CompraComponent},
          {path:'compra/ingresar',component:IngresarComponent},
          {path:'pieza',component:PiezaComponent},
          {path:'pieza/ingresar',component:IngresarPiezaComponent},
        ]
      }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports:[RouterModule]
  })
  export class PagesRoutingModule { }