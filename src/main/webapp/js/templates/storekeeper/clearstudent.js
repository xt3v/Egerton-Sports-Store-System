let clearStudentTemplate=` <div id="add-item-wrapper" class="card">
<div class="card-head">
     <h4 class="text-center">Clear Student</h4>
</div>
<div class="card-body">
   <form>
       <div class="form-group">
          <label for="regno">RegNo:</label>
          <input class="form-control" type="text" name="regno" id="regno" >
        </div>
        <div class="form-group">
          <label for="name">Student Name:</label>
          <input class="form-control" type="text" name="studentname" id="studentname">
        </div>
        <div class="form-group">
          <style>
            #clearstudent{
  font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
  border-collapse: collapse;
    width: 80%;
    border: 1px solid  black;
  }
  #claerstudent td, #clearstudent th,{
    border: 1px solid black;
  padding: 8px;
  
  }
  


  #clearstudent th {
  padding-top: 12px;
  padding-bottom: 12px;
  text-align: left;
  background-color: #4CAF50;
  color: white;
}
table, td, th {
  border: 1px solid black;
}

table {
  border-collapse: collapse;
  width: ;
}

th {
  height: ;}

          </style>
          <label for="borroweditem">Borrowed Item/s:</label>
          <table id="clearstudent">
    <thead>
    <tr>
        <th>Item Name</th>
        <th>Quantity</th>
        <th>Cleared Quantity</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td> E.g Hockey stick</td>
        <td>5</td>
        <td></td>
    </tr>
    <tr>
        <td>E.g rugby ball</td>
        <td>20</td>
        <td></td>
    </tr>
    </tbody>
</table>

          
        </div>
        <div class="form-group">
          <style>
            #clearstudent{
  font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
  border-collapse: collapse;
    width: 80%;
    border: 1px solid  black;
  }
  #claerstudent td, #clearstudent th,{
    border: 1px solid black;
  padding: 8px;
  
  }
  


  #clearstudent th {
  padding-top: 12px;
  padding-bottom: 12px;
  text-align: left;
  background-color: #4CAF50;
  color: white;
}
table, td, th {
  border: 1px solid black;
}

table {
  border-collapse: collapse;
  width: ;
}

th {
  height: ;}

          </style>
          <label for="borroweditem">Lost Items:</label>
          <table id="clearstudent">
    <thead>
    <tr>
        <th>Item Name</th>
        <th>Quantity</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td>Hockey stick</td>
        <td>5</td>
        
    <tr>
        <td>rugby ball</td>
        <td>20</td>
        
    </tr>
    </tbody>
</table>
        </div>
       <div>
          <button class="btn btn-success">
             Save
          </button>
          <button class="btn btn-danger">
              Clear
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
  width: 80%;
  height: 650px;
}
#regno{
width: 50%;
}
#studentname{
  width: 50%;
}

</style>`