import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    accessToken: '',
    user: '',

    people: '', // 인원
    money: '', // 예산

    // productlist: [{ pro_name: '', price: '', quantity: '' }]
    pro_name: '', // 상품명
    price: '', // 가격
    quantity: '' // 수량

  },
  computed: {

  },
 
  
})
