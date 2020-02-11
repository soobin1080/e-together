import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    accessToken: localStorage.getItem('accessToken'),
    user: localStorage.getItem('user'),
    email: localStorage.getItem('email'),
    personnel: '', // 인원
    budget: '', // 예산 
    tokenType: '',
    budgetlist : [],
    budgetListBar: [],
    budgetListBarETC: [],
    ETC: ['수산물/해산물', '쌀/잡곡', '즉석식품', '과일', '스낵', '견과/건해산물'],
    mainTotal: 0,
    etcTotal: 0,
  },
  getters : {
    isLoggedIn: function(state) {
      return state.accessToken ? true : false
    },
    requestHeader : function(state) {
      return {
      headers: {
          'Authorization': 'Bearer ' + state.accessToken
        }
      }
    },
    userId: function(state) {
      return state.user ? state.user : ""
    }
  },
  mutations : {
    setToken: function(state, token){
      state.accessToken = token
    },
    deleteProduct: function(state, idx) {
      console.log('deleteProduct')
      // var i = state.budgetlist.indexOf(idx)
      const product = state.budgetlist[idx]
      console.log(product)
      if (state.ETC.includes(product.category)) {
        console.log(`기타에서 ${product.price * product.quantity}원 차감`)
        state.etcTotal -= (product.price)
      } else {
        console.log(`${product.category}에서 ${product.price * product.quantity}원 차감`)
        state.mainTotal -= (product.price)
      }
      // state.total -= state.budgetlist[i].price;
      state.budgetlist.splice(idx,1);

      // this.budgetalert();
    },

    addCart: function(state, product) {
      console.log('addCart')
      console.log(product)
      const idx = state.budgetlist.findIndex(function (budget) {
        return budget.pro_name === product.pro_name
      })

      if (idx === -1) {
          state.budgetlist.push(product)
      }
      else {
        state.budgetlist[idx].quantity += product.quantity
        state.budgetlist[idx].price += (product.quantity * product.price)
      }

      if (state.ETC.includes(product.category)) {
        state.etcTotal += (product.quantity * product.price)
      } else {
        state.mainTotal += (product.quantity * product.price)
      }

      console.log(state.budgetListBar)
      console.log(state.budgetListBarETC)
    },
    cartClear: function(state) {
      state.budgetlist = []
    },

    addBudgetBar: function(state, product) {
      console.log('addBudgetBar')
      console.log(product.isETC)
      if (product.isETC == true) {
        const barIdx = state.budgetListBarETC.findIndex( budget => {
          return budget.category === product.category
        })

        if (barIdx === -1) {
          state.budgetListBarETC.push({
            category: product.category,
            price : (product.price * product.quantity)
          })
        } else {
            state.budgetListBarETC[barIdx].price += (product.price * product.quantity)
          }
          state.etcTotal += (product.price*product.quantity);

      } else {
        const barIdx = state.budgetListBar.findIndex( budget => {
          return budget.category === product.category
        })

        if (barIdx === -1) {
          state.budgetListBar.push({
            category: product.category,
            price : (product.price * product.quantity)
          })
        } else {
            // state.budgetListBar[barIdx].price += (product.price *product.quantity)
          }
        // state.mainTotal += (product.price*product.quantity);
      }

      console.log(state.budgetListBar)
      console.log(state.budgetListBarETC)
    },

    changeQuantity : function(state, productInfo) {
      // if 
      const idx = state.budgetlist.findIndex( budget => {
        return budget.pro_id === productInfo.product_id
      })
      
      }

  },

  actions : {
 
  login: function(options, token) {
    options.commit('setToken', token)
  },

  logout: function(options) {
    options.commit('setToken')
  },
  
  addCartAsync: function(options, product) {
    console.log('addCartAsync')
    options.commit('addCart', product)
  },

  deleteProductAsync: function(options, idx) {
    options.commit('deleteProduct', idx)
  },

  addBudgetBarAsync: function(options, product) {
    console.log('addBudgetBarAsync')
    console.log(product)
    options.commit('addBudgetBar', product)
    // if (product.isETC == true) { // 기타 품목에 들어가는 경우
    //   options.commit('addBudgetBar', product, state.budgetListBarETC)
    // } else {
    //   options.commit('addBudgetBar', product, state.budgetListBar)
    // }
  },

  deleteBudgetBarAsync: function(options, product) {
    options.commit('deleteBudgetBar', product)
  },

  changeQuantityAsync: function(options, productInfo) {
    options.commit('changeQuantity', productInfo)
  },
}
})