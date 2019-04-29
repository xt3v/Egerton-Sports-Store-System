let registerStudentTemplate = `<div id="register-student-window">
<h4 class="text-center" >Regsiter Student</h4> 
<form class="form" id="registerStudent-form">
    <div class="form-group">
        <pre>Name : </pre><input type="text" id="student-name" name="student-name" class="form-control">
   </div>
   <div class="form-group">
      <pre>RegNo :  </pre><input type="text" id="student-regNo" name="student-regNo" class="form-control">
   </div>

  <div class="form-group">
     <pre>Phone No :  </pre><input type="text" id="phone-no" name="phone-no" class="form-control">
  </div>
  <div class="form-group">
    <pre>Residence : </pre><input type="text" id="residence" name="residence" class="form-control">
  </div>

  <div>
     <button class="btn btn-primary">Save</button>
     <button class="btn btn-danger">Cancel</button>
  </div>
</form>
</div>

<style>
    #register-student-window{
       width:60% ;
       background-color:white !important;
       border-radius:20px;
       padding:20px;
    }
</style>
`;

