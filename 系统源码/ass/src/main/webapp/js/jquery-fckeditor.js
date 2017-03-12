(function(jQuery){
	$.fckeditor = function(name){
		var oFCKeditor = new FCKeditor(name);
		oFCKeditor.BasePath	= "fckeditor/" ;
		oFCKeditor.ToolbarSet = "Default";
		oFCKeditor.ReplaceTextarea() ;
	}
})(jQuery);
