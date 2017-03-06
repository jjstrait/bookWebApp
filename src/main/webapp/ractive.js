/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$.get( 'tempFooter.html' ).then( function ( template ) {
  ractive = new Ractive({
    el: '#tempFooter',
    template: template
    // ...
  });
});