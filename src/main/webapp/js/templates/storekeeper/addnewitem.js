let addNewItemTemplate = `<div id="add-item-wrapper" class="card">
<div class="card-head">
     <h4 class="text-center">Add New Item</h4>
</div>
<div class="card-body">
   <form>
       <div class="form-group">
          <label for="sport">Sport</label>
          <select name="sport" id="sport" class="form-control">
              <option value="">Football</option>  
              <option value="">Rugby</option>  
         </select>
       </div>
       <div class="form-group">
             <label for="name">Name</label>
             <input class="form-control" type="text" name="name" id="name">
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

<style>
#add-item-wrapper{
  padding: 30px;
  border-radius: 5%;
  width: 60%;
  height: 400px;
}
</style>`