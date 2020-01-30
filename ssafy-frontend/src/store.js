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
  // mutations: {
  //   addCart: (state, payload) => {
  //     if (payload.product !== '') {
  //       localStorage.setItem(payload.product, JSON.stringify({
  //         todo: payload.product,
  //         done: false
  //       }));

  //       state.productlist.push({
  //         product: payload.product,
  //         done: false,
  //       });
  //     } else {
  //       alert('할 일을 입력해주세요!');
  //     }
  //   }
  // }
  
})
