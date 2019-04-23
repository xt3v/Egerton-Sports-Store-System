let registerStudentTemplate = `<div id="registerStudent-window" >
<form class="form" id="registerStudent-form">
    <div class="form-group" style="width: 40%;">
          <label for="name"><pre>Name : </pre></label>
         <input type="text" id="student-name" name="student-name" class="form-control">
   </div>
   <div class="form-group" style="width: 40%;">
       <label for="regNo"><pre>RegNo :  </pre></label>
      <input type="text" id="student-regNo" name="student-regNo" class="form-control">
   </div>

  <div class="form-group" style="width: 40%;">
     <label for="phone-no"><pre>Phone No :  </pre></label>
     <input type="text" id="phone-no" name="phone-no" class="form-control">
  </div>
  <div class="form-group" style="width: 40%;">
     <label for="residence"><pre>Residence :  </pre></label>
     <input type="text" id="residence" name="residence" class="form-control">
  </div>

  <div style="width:40%;">
     <button class="btn btn-primary">Save</button>
     <button class="btn btn-danger">Cancel</button>
  </div>
</form>
</div>`;