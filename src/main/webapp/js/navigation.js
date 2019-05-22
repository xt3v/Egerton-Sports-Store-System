addScript("js/templates/storekeeper/addnewitem.js")
addScript("js/templates/storekeeper/registerstudent.js")
addScript("js/templates/storekeeper/clearstudent.js")
addScript("js/templates/storekeeper/returnitems.js")
addScript("js/templates/storekeeper/recoverlostitems.js")
addScript("js/templates/storekeeper/viewstock.js")


function viewStock(){
  addToMain(viewStockTemplate)
  
}

function registerStudent(){
	addToMain(registerStudentTemplate)
}

function clearStudent(){
	addToMain(clearStudentTemplate)
}


function recoverLostItems(){
    addToMain(recoverLostItemsTemplate)
    setupRecoverLostItemsTemplate()
}

function addNewItem(){
   addToMain(addNewItemTemplate)
}

function returnItems(){
   addToMain(returnItemTemplate)
   setupReturnedItemsTemplate()
}

function addScript(path){
    var imported = document.createElement('script');
    imported.src = path;
    document.head.appendChild(imported);
}


function addToMain(template){
    var mainWindow = document.getElementById("main-window") 
    mainWindow.innerHTML = template
}
