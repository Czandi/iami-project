import { Injectable } from '@angular/core';
import {Router} from "@angular/router";

const TOKEN_KEY = 'auth-token';
const USER_KEY = 'auth-user';

@Injectable({
  providedIn: 'root',
})
export class TokenStorageService {
  constructor(
    private router: Router
  ) {}

  signOut(): void {
    window.sessionStorage.clear();
    this.router.navigate(['/login'])
  }

  public saveToken(token: string): void {
    window.sessionStorage.removeItem(TOKEN_KEY);
    window.sessionStorage.setItem(TOKEN_KEY, token);
  }

  //Jak zrobisz funkcje get, to mozesz odwolywac sie do tych wartosci za pomoca np objekt.token, a nie objekt.getToken()
  public get token(): string {
    return sessionStorage.getItem(TOKEN_KEY);
  }

  public saveUser(user): void {
    window.sessionStorage.removeItem(USER_KEY);
    window.sessionStorage.setItem(USER_KEY, JSON.stringify(user));
  }

  public getUser(): any {
    return JSON.parse(sessionStorage.getItem(USER_KEY));
  }
}
