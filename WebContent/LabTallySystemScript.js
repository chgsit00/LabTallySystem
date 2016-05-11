$(document).ready(function() {
	$('.Laborblatt').hide();
	$('.LaborblattNav').click(pickLaborBlatt);
});

pickLaborBlatt = function() {
	$('.Laborblatt').hide();
	$('.LaborblattNav').removeClass('LaborblattSelected');
	$(this).addClass('LaborblattSelected');
	var classList = $(this).attr("class").split(" ");
	var className = classList[1];
	console.log(className);
	$('#'+className).show();
}
