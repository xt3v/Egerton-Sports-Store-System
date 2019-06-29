<%
  if(request.getCookies() != null && request.getCookies().length > 3)response.sendRedirect("/home.jsp");
%>
<!DOCTYPE html>
   <html class="no-js"> <!--<![endif]-->
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>Login | Sports Department</title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1">
         <!-- Custom styles for this template-->
        <link href="css/sb-admin.css" rel="stylesheet">
        <link rel="stylesheet" href="./vendor/fontawesome-free/css/all.min.css">
        <link rel="stylesheet" href="vendor/parsley/parsley.css">
        <link rel="stylesheet" href="vendor/sweetalert/sweetalert2.min.css">  
        <link rel="stylesheet" href="css/login.css">
    </head>
    <body>
        <div class="container-fluid main-container p-4">
            <div class="row">
              
                 <div class="col-md-12 d-flex icon-eger justify-content-center head">
                     <h3 style="color:white;">Egerton University Sports Department</h3>
                </div>
            </div>
            <div class="row d-flex p-4 justify-content-center">
                <div class="col-md-4" id="loginForm">
                    <div class="card">
                        <article class="card-body">
                            <h4 class="card-title text-center mb-4 mt-1">Sign in</h4>
                            <hr>
                            <%
                                String errorHtml = "";
                                Object error = request.getAttribute("error");
                                if(error != null){
                                    errorHtml = "<p class=\"text-danger text-center\">"+request.getAttribute("message")+"</p>";
                                }
                            %>
                            <%=errorHtml%>
                            <form id="loginFormR" parsley-validate action="/login" method="POST">
                            <div class="form-group">
                            <div class="input-group">
                                <div class="input-group-prepend">
                                    <span class="input-group-text"> <i class="fa fa-user"></i> </span>
                                 </div>
                                <input id="loginId" name="loginId" class="form-control" placeholder="Reg No /Employee ID" type="text" required="true">
                            </div> 
                            </div> 
                            <div class="form-group">
                            <div class="input-group">
                                <div class="input-group-prepend">
                                    <span class="input-group-text"> <i class="fa fa-lock"></i> </span>
                                 </div>
                                <input id="passwordLogin" name="passwordLogin" class="form-control" placeholder="password" type="password"required="true">
                            </div> 
                            </div> 
                            <div class="form-group">
                            <button type="submit" class="btn btn-primary btn-block" id="loginBtn"> Login  </button>

                            </div> 
                            <p class="text-center"><a href="#" class="btn" id="signUpFormSet">Sign Up</a></p>
                            <p class="text-center"><a href="#" class="btn" id="forgot-password">Forgot password?</a></p>
                            </form>
                        </article>
                        </div> 
                        
                </div>
                <div class="col-md-5" id="signUpForm" style="display:none !important;">
                        <div class="card">
                            <article class="card-body">
                                <h4 class="card-title text-center mb-4 mt-1">Student's Sign Up</h4>
                                <hr>
                                <p class="text-danger text-center" id="signUpMessage"></p>
                                <form id="signUpFormR" parsley-validate>
                                <div class="form-group">
                                <div class="input-group">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text"> <i class="fa fa-user"></i> </span>
                                     </div>
                                    <input id="regNo" name="regNo" class="form-control" parsley-trigger="change" placeholder="Registraion Number" type="text" required="true">
                                </div> <!-- input-group.// -->
                                </div> <!-- form-group// -->
                                <div class="form-group">
                                    <div class="input-group">
                                        <div class="input-group-prepend">
                                           <span class="input-group-text"> <i class="fa fa-user"></i> </span>
                                        </div>
                                        <input id="name" id="name" class="form-control" parsley-trigger="change" placeholder="Name" type="text" required="true">
                                    </div> <!-- input-group.// -->
                                </div>
                                <div class="form-group">
                                    <div class="input-group">
                                        <div class="input-group-prepend">
                                            <span class="input-group-text"> <i class="fa fa-user"></i> </span>
                                        </div>
                                        <input name="phoneNo" id="phoneNo" parsley-trigger="change" parsley-regex-message="Please enter a valid Kenyan mobile phone number."
                                        data-parsley-pattern="^(?:254|\+254|0)?(7(?:(?:[129][0-9])|(?:0[0-8])|(4[0-1]))[0-9]{6})$"
                                        parsley-regexp="^(?:254|\+254|0)?(7(?:(?:[129][0-9])|(?:0[0-8])|(4[0-1]))[0-9]{6})$" class="form-control" placeholder="Phone Number" type="text" required="true">
                                    </div> <!-- input-group.// -->
                                </div>
                                       
                                <div class="form-group">
                                    <div class="input-group">
                                        <div class="input-group-prepend">
                                            <span class="input-group-text"> <i class="fa fa-user"></i> </span>
                                        </div>
                                        <input name="residence" id="residence" parsley-trigger="change" class="form-control" placeholder="Place of Residence" type="text" required="true">
                                    </div> <!-- input-group.// -->
                                </div>
                                                
                                <div class="form-group">
                                <div class="input-group">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text"> <i class="fa fa-lock"></i> </span>
                                     </div>
                                    <input id="password"  data-parsley-minlength="8" name="password" parsley-trigger="change" class="form-control" placeholder="password" type="password" required="true">
                                </div> <!-- input-group.// -->
                                </div> <!-- form-group// -->
                                <div class="form-group">
                                    <div class="input-group">
                                        <div class="input-group-prepend">
                                            <span class="input-group-text"> <i class="fa fa-lock"></i> </span>
                                        </div>
                                        <input id="confirm-password" data-parsley-equalto="#password" parsley-trigger="change" name="confirm-password" class="form-control" placeholder="Confirm password" type="password" required="true">
                                        </div> <!-- input-group.// -->
                                        </div> <!-- form-group// -->
                                <div class="form-group">
                                 <button type="submit" class="btn btn-primary btn-block" id="signUpBtn"> Sign Up </button>
                                 <p class="text-center"><a href="#" class="btn" id="loginFormSet">Login</a></p>
                                </div> <!-- form-group// -->
                                  
                                </form>
                            </article>
                            </div> <!-- card.// -->
                            
                    </div>
            </div>
        </div> 
         <!-- Bootstrap core JavaScript-->
        <script src="vendor/jquery/jquery.min.js"></script>
        <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
        <script src="vendor/sweetalert/sweetalert2.min.js"></script>
        <script src="vendor/axios.min.js"></script>
        <script src="vendor/parsley/parsley.min.js"></script>
        <script src="js/login.js"></script>
      </body>
</html>