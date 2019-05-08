let returnItemTemplate = `
<div id="return-Item-wrapper" class="card">
          <div class="card-head">
               <h4 class="text-center">Return Item</h4>
          </div>
          <div class="card-body">
             <form>
                 <div class="form-group">
                    <label>Registration/Employee Id</label> 
                    <div id="reg-div">
                        <input type="text" id="idNo" class="form-control">
                        <button class="btn btn-primary">Search</button> 
                    </div>       
                 </div>
                 <div id="table-div">
                     <table class="table table-bordered table-stripped">
                         <thead class="thead-dark">
                            <tr>
                              <th>Item Name</th>
                              <th>Amount</th>
                              <th></th>  
                            </tr>            
                         </thead> 
                         <tbody>
                           <tr>
                             <td>Football</td>
                             <td>2</td>
                             <td>
                                 <button class="return-btn btn btn-primary">return</button>
                             </td> 
                           </tr>
                         </tbody>       
                     </table>
                 </div>
             </form>
          </div>
          </div>

          <div class="modal fade" id="returnModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
              <div class="modal-dialog" role="document">
                <div class="modal-content">
                  <div class="modal-header">
                    <h3 id="returnItemName" class="text-center">Item Name</h3>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                      <span aria-hidden="true">&times;</span>
                    </button>
                  </div>
                  <div class="modal-body">
                    <form>
                        <div class="form-group">
                           <h4 class="text-center">Borrowed Amount : <label id="borrowedAmount">3</label>  
                           </h4>
                           <div style="display:flex;justify-content: center;">
                             <label><h6>Returned Amount</h6></label>
                             <input style="width:40%;margin-left: 30px;" class="form-control" type="number" id="returnedAmount">
                           </div>
                        </div>
                    </form>
                  </div>
                  <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    <button type="button" class="btn btn-primary">Save</button>
                  </div>
                </div>
              </div>
            </div>
         <style>
          #return-Item-wrapper{
            padding: 30px;
            border-radius: 5%;
            width: 60%;
            height: 400px;
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
function setupReturnedItemsTemplate(){
  $(".return-btn").on('click',function(e){
    e.preventDefault()
    $("#returnModal").modal("show") 
  });   
}
