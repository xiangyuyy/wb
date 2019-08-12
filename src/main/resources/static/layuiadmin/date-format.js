Date.prototype.Format = function(fmt) {
	var dateee = new Date(fmt).toJSON();
	//var dateee = new Date("2017-07-09T09:46:49.667").toJSON();       
   if(fmt == null){
		return "";
   }
	var date = new Date(+new Date(dateee) + 8 * 3600 * 1000).toISOString()
			.replace(/T/g, ' ').replace(/\.[\d]{3}Z/, '')
	return date;

}
