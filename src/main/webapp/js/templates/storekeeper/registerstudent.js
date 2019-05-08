let registerStudentTemplate=`<div id="add-item-wrapper" class="card">
<div class="card-head">
     <h4 class="text-center">Register Student</h4>
</div>
<div class="card-body">
   <form>
       <div class="form-group">
          <label for="name">Name</label>
          <input class="form-control" type="text" name="name" id="name">
          
       </div>
       <div class="form-group">
             <label for="regno">RegNo.</label>
             <input class="form-control" type="text" name="regno" id="regno">
       </div>
       <div class="form-group">
             <label for="regno">Residence</label>
             <input class="form-control" type="text" name="residence" id="residence">
       </div>
       
     <div class="form-group">
      <label for="phoneNo">Phone No.</label>
      <input class="form-control" type="text" name="phoneNo" id="phoneNo">
     </div>
    
      <div >
        <label for="captain">Captain</label>
       <input  type="checkbox" name="" class="">
     
     </div>
    

       <div>
          <button class="btn btn-success">
             Save
          </button>
          <button class="btn btn-danger">
              Cancel
           </button>
       </div>
   </form>
</div>
</div>

  </div>
  <style>
#add-item-wrapper{
  padding: 30px;
  border-radius: 5%;
  width: 60%;
  height: 540px;
}
</style>
`