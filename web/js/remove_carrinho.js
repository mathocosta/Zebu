(function() {
  const allRemoveBtn = document.getElementsByClassName('remove-btn')
  
  function removeCarrinho(cod) {
    let req = new XMLHttpRequest()
    req.onreadystatechange = function () {
      if (this.readyState == 4 && this.status == 200) {
        alert('Produto removido do carrinho!')
        location.reload()
      }
    }
    req.open('GET', `/Zebu/removecarrinho?codigo=${encodeURIComponent(cod)}`)
    req.send()
  }

  for (var i = 0; i < allRemoveBtn.length; i++) {
    allRemoveBtn[i].addEventListener('click', function () {
      let codigo = this.getAttribute('data-prod-cod');
      removeCarrinho(codigo)
    })
  }
})()