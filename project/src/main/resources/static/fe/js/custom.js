// JavaScript Document
$(function() {
 "use strict";

  function responsive_dropdown () {
    /* ---- For Mobile Menu Dropdown JS Start ---- */
      $('#menu span.opener').on("click", function() {
        if ($(this).hasClass("plus")) {
          $(this).parent().find('.mobile-sub-menu').slideDown();
          $(this).removeClass('plus');
          $(this).addClass('minus');
        }
        else
        {
          $(this).parent().find('.mobile-sub-menu').slideUp();
          $(this).removeClass('minus');
          $(this).addClass('plus');
        }
        return false;
      });
    /* ---- For Mobile Menu Dropdown JS End ---- */
    /* ---- For Sidebar JS Start ---- */
      $('.sidebar-box span.opener').on("click", function(){
      
        if ($(this).hasClass("plus")) {
          $(this).parent().find('.sidebar-contant').slideDown();
          $(this).removeClass('plus');
          $(this).addClass('minus');
        }
        else
        {
          $(this).parent().find('.sidebar-contant').slideUp();
          $(this).removeClass('minus');
          $(this).addClass('plus');
        }
        return false;
      });
    /* ---- For Sidebar JS End ---- */
    /* ---- For Footer JS Start ---- */
      $('.footer-static-block span.opener').on("click", function(){
      
        if ($(this).hasClass("plus")) {
          $(this).parent().find('.footer-block-contant').slideDown();
          $(this).removeClass('plus');
          $(this).addClass('minus');
        }
        else
        {
          $(this).parent().find('.footer-block-contant').slideUp();
          $(this).removeClass('minus');
          $(this).addClass('plus');
        }
        return false;
      });
    /* ---- For Footer JS End ---- */
  }

  function owlcarousel_slider () {
    /* ------------ OWL Slider Start  ------------- */
      /* ----- pro_cat_slider Start  ------ */
      $('.pro_cat_slider').owlCarousel({
        items: 4,
        navigation: true,
        pagination: false,
        itemsDesktop : [1199, 3],
        itemsDesktopSmall : [991, 3],
        itemsTablet : [768, 2],
        itemsTabletSmall : false,
        itemsMobile : [479, 1]
      });
      /* ----- pro_cat_slider End  ------ */
      /* ----- brand-logo Start  ------ */
      $('#brand-logo').owlCarousel({
        items: 5,
        navigation: true,
        pagination: false,
        itemsDesktop : [1199, 3],
        itemsDesktopSmall : [991, 3],
        itemsTablet : [768, 2],
        itemsTabletSmall : false,
        itemsMobile : [479, 1]
      });
      /* ----- brand-logo End  ------ */
      /* ---- Testimonial Start ---- */
      $("#client, .main-banner").owlCarousel({
     
        //navigation : true,  Show next and prev buttons
        slideSpeed : 300,
        paginationSpeed : 400,
        autoPlay: false,
        pagination:true,
        singleItem:true,
        navigation:true
      });
      /* ----- Testimonial End  ------ */
      /* ----- Sub-banner Strat  ------ */
      $('#sub-banner-main').owlCarousel({
      items: 4,
      navigation: true,
      pagination: false,
      itemsDesktop : [1199, 3],
      itemsDesktopSmall : [991, 3],
      itemsTablet : [768, 2],
      itemsTabletSmall : [599,2],
      itemsMobile : [419, 1]
      });
      /* ----- Sub-banner End  ------ */
      /* ----- Blog Strat  ------ */
      $('.home-blog-item').owlCarousel({
      items: 2,
      navigation: true,
      pagination: false,
      itemsDesktop : [1199, 2],
      itemsDesktopSmall : [991, 2],
      itemsTablet : [768, 2],
      itemsTabletSmall : false,
      itemsMobile : [599, 1]
      });
      /* ----- Blog End  ------ */
    /* ------------ OWL Slider End  ------------- */
  }

  function scrolltop_arrow () {
   /* ---- Page Scrollup JS Start ---- */
   //When distance from top = 250px fade button in/out
    $(window).scroll(function(){
        if ($(this).scrollTop() > 250) {
            $('#scrollup').fadeIn(300);
        } else {
            $('#scrollup').fadeOut(300);
        }

        /* header-fixed JS Start */
        if ($(this).scrollTop() > 100){  
          $('header').addClass("header-fixed");
        }
        else{ 
          $('header').removeClass("header-fixed");
        }
    });
    //On click scroll to top of page t = 1000ms
    $('#scrollup').on("click", function(){
        $("html, body").animate({ scrollTop: 0 }, 1000);
        return false;
    });
    /* ---- Page Scrollup JS End ---- */
  }

  function custom_tab() {
   /* ----------- product category Tab Start  ------------ */
    $('.tab-stap').on('click', 'li', function() {
        $('.tab-stap li').removeClass('active');
        $(this).addClass('active');
        
        $(".product-slider-main").fadeOut();
        var currentLiID = $(this).attr('id');
        $("#data-"+currentLiID).fadeIn();
        return false;
    });
    /* ------------ product category Tab End  ------------ */
    /* ------------ Account Tab JS Start ------------ */
    $('.account-tab-stap').on('click', 'li', function() {
        $('.account-tab-stap li').removeClass('active');
        $(this).addClass('active');
        
        $(".account-content").fadeOut();
        var currentLiID = $(this).attr('id');
        $("#data-"+currentLiID).fadeIn();
        return false;
    });
    /* ------------ Account Tab JS End ------------ */
  }

  function setminheight() {
    $( ".pro_cat" ).css("min-height",$(".product-slider-main").height() );
    $( ".pro-detail-main" ).css("min-height",$(".special-products-block").height() );
  }

  /* Price-range Js Start */
  function price_range () {
      $( "#slider-range" ).slider({
      range: true,
      min: 0,
      max: 800,
      values: [ 75, 500 ],
      slide: function( event, ui ) {
      $( "#amount" ).val( "$" + ui.values[ 0 ] + " - $" + ui.values[ 1 ] );
      }
      });
      $( "#amount" ).val( "$" + $( "#slider-range" ).slider( "values", 0 ) +
      " - $" + $( "#slider-range" ).slider( "values", 1 ) );
  }
  /* Price-range Js End */

  /*Video_Popup Js Start*/
  function video_popup() {
    $('.popup-youtube').magnificPopup({
        disableOn: 700,
        type: 'iframe',
        mainClass: 'mfp-fade',
        removalDelay: 160,
        preloader: false,
        fixedContentPos: false
    });
  }
  /*Video_Popup Js Ends*/

  /* Product Detail Page Tab JS Start */
  function description_tab () {
    $("#tabs li a").on("click", function(e){
      var title = $(e.currentTarget).attr("title");
      $("#tabs li a").removeClass("selected")
      $(".tab_content li div").removeClass("selected")
      $(".tab-"+title).addClass("selected")
      $(".items-"+title).addClass("selected")
      $("#items").attr("class","tab-"+title);

      return false;
    });
  }
  /* Product Detail Page Tab JS End */

  $(document).ready(function() {
    owlcarousel_slider(); price_range (); responsive_dropdown(); setminheight(); description_tab (); custom_tab (); scrolltop_arrow (); video_popup ();

    $(function(){
      $.stellar({
        horizontalScrolling: false,
        verticalOffset: 40
      });
    });
  });

  $( window ).on( "resize", function() {
    setminheight();
  });
});

  $( window ).on( "load", function() {
    // Animate loader off screen
    $(".se-pre-con").fadeOut("slow");
  });
