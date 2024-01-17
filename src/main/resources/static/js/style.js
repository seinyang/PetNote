// 메뉴 클릭시 부드럽게 이동 //
$(function() {
	$('a.mn_li').bind('click', function(event) {
		var $anchor = $(this);
		$('html, body').stop().animate({
			scrollTop: $($anchor.attr('href')).offset().top
		}, 1000, 'easeInOutExpo');
		event.preventDefault();
		/* $(".intro_wp").css("margin-top","100px"); */
	});
});

// 탑으로 부드럽게 올라가기 //
$( document ).ready( function() {
  $( window ).scroll( function() {
    if ( $( this ).scrollTop() > 1400 ) {
      $( '.m_top' ).fadeIn();
    } else {
      $( '.m_top' ).fadeOut();
    }
  });
  $( '.m_top' ).click( function() {
    $( 'html, body' ).animate( { scrollTop : 0 }, 1000 );
    return false;
  });
});

// simple smooth scrolling for bootstrap scroll spy nav
// credit http://stackoverflow.com/questions/14804941/how-to-add-smooth-scrolling-to-bootstraps-scroll-spy-function
