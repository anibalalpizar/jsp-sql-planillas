<%-- 
    Document   : registro
    Created on : Dec 3, 2022, 9:46:54 PM
    Author     : anibal
--%>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Usuario</title>
        <link href="css/themes/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="css/alertify.min.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    </head>
    <body>

        <br><div class="container-fluid">
            <div class="row">
                <div class="col-md-4">
                    <div class="card col-sm-12">
                        <div class="card-body">
                            <form class="form-sign" action="registro.jsp" method="post" onsubmit="return validarPasswd()">
                                <div class="form-group text-center">
                                    <h3>Registrar Usuario</h3>
                                    <!--<img src="../java/imagenes/logo.png" alt="" width="170px"/><br>-->                              
                                    <img src="../java/imagenes/logo.png" alt=""/>
                                    <label>Rellena el formulario</label>
                                </div>
                                <div class="form-group">
                                    <label>Usuario:</label>
                                    <input type="text" name="txtUser" id="txtUser" value="" required="" placeholder="Crea tu Usuario" class="form-control">
                                </div>
                                <div class="form-group">
                                    <label>Clave:</label>
                                    <input type="password" name="txtClave" id="txtClave" value="" required="" placeholder="*****" class="form-control">
                                </div>
                                <div class="form-group">
                                    <label>Confirma Clave:</label>
                                    <input type="password" name="txtClave2" id="txtClave2" value="" required="" placeholder="*****" class="form-control">
                                </div>
                                <div class="form-group">
                                    <label>Status:</label>
                                    <input type="text" name="txtStatus" id="txtStatus" value=""  required=""  placeholder="Ingresa el estado" class="form-control">
                                </div>
                                <div class="form-group">
                                    <label>IdPosition</label>
                                    <input type="text" name="txtIdPosition" id="txtIdPosition" value=""  required=""  placeholder="Ingresa id positoin" class="form-control">
                                </div>
                                <input type="submit" name="btnRegistrar" value="Registrar" class="btn btn-primary">
                                <a href="#" class="btn btn-danger">Cancelar</a>
                            </form>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </body>
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    <script src="alertify.min.js" type="text/javascript"></script>
    <script>
                                function validarPasswd() {
                                    var p1 = document.getElementById("txtClave").value;
                                    var p2 = document.getElementById("txtClave2").value;

                                    var espacios = false;
                                    var cont = 0;
                                    while (!espacios && (cont < p1.length)) {
                                        if (p1.charAt(cont) == " ")
                                            espacios = true;
                                        cont++;
                                    }
                                    if (espacios) {
                                        alertify.alert("Error", "La contraseña no puede contener espacios en blanco").set('label', 'Ok');
                                        return false;
                                    }

                                    if (p1.length == 0 || p2.length == 0) {
                                        alertify.alert("Error", "Los campos de password no pueden quedar vacios").set('label', 'Ok');
                                        return false;
                                    }

                                    if (p1 != p2) {
                                        alertify.alert("Error", "Las contraseñas son incorrectas").set('label', 'Ok');
                                        return false;

                                    } else {
                                        alertify.success("Todo esta correcto");
                                        return true;
                                    }
                                }
    </script>
</html>

