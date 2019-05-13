let issueItemTemplate = `
<div id="issue-Item-wrapper" class="card">
<div class="card-head">
     <h4 class="text-center">Issue Item</h4>
</div>
<div class="card-body">
   <form>
       <div class="form-group">
          <label>Registration/Employee Id</label> 
          <div id="reg-div">
              <input type="text" id="idNo" class="form-control">
           </div>
           <div>
              <button class="btn btn-primary ">Student</button> 
              <button class="btn btn-primary ">Coach</button> 
           </div>       
       </div>
       <div id="table-div">
           <table class="table table-bordered table-stripped">
               <thead class="thead-dark">
                 <label>Details</label>
                  <tr>
                    <th>Item Name</th>
                    <th>ID</th>
                    <th>Status</th>
                    <th>Amount</th>   
                  </tr>            
               </thead> 
               <tbody>
                 <tr>
                   <td>Footwear</td>
                   <td>Fta178qpG</td>
                   <td>Present</td>
                   <td>4</td> 
                 </tr>
                 <tr>
                   <td></td>
                   <td></td>
                   <td></td>
                   <td></td>
                  </tr>
                 <tr></tr>
               </tbody>       
           </table>
           <div>
              <select class="return-btn">Sports</select>
              <button class="return-btn btn btn-danger">Go</button>
           </div>
       </div>
   </form>
</div>
</div>
<style>
#issue-Item-wrapper{
  padding: 30px;
  border-radius: 5%;
  width: 60%;
  height: 500px;
  }

  #reg-div{
    display: flex;
    justify-content: flex-start;
  }

  #reg-div .form-control{
     width: 60% !important;
  }
  
  #reg-div button{
     width: 20% !important;
     margin-left: 30px;
  }
</style>
`