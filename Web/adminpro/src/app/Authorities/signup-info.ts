export class SignUpInfo {
    nombres: string;
    apellidos:String;
    username: string;
    email: string;
    role: string[];
    password: string;

    constructor(nombres: string,apellidos:String, username: string, email: string, password: string) {
        this.nombres = nombres;
        this.apellidos=apellidos;
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = ['user'];
    }
}
