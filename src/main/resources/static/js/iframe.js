function ajustar()
{
try{    
var oBody       =       ifrm.document.body;
var oFrame      =       document.all("ajuste_iframe");          
oFrame.style.height = oBody.scrollHeight + (oBody.offsetHeight - oBody.clientHeight);
oFrame.style.width = oBody.scrollWidth + (oBody.offsetWidth - oBody.clientWidth);
}
catch(e)
{
window.status = 'Error: ' + e.number + '; ' + e.description;
}
}