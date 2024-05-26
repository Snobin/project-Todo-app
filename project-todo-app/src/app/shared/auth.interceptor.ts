import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor,
  HTTP_INTERCEPTORS
} from '@angular/common/http';
import { Observable } from 'rxjs';
import { AuthService } from '../services/auth.service';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  constructor(private auth: AuthService) {

  }
  intercept(
      req: HttpRequest<any>,
      next: HttpHandler
  ): Observable<HttpEvent<any>> {
      let authReq = req;
      const token = this.auth.getToken();
      if (token != null) {
          authReq = authReq.clone({
              setHeaders: { Authorization: `Bearer ${token}` },
          });
      }
      return next.handle(authReq);
  }
}

export const authInterceptorProviders = [
  {
      provide:HTTP_INTERCEPTORS,
      useClass:AuthInterceptor,
      multi:true,
  },
];
