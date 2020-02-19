import Vue from 'vue'
import Vuex from 'vuex'
import VueSession from 'vue-session'

Vue.use(Vuex)
Vue.use(VueSession)
// Vue.use(VueSessionStorage)
export default new Vuex.Store({
  state: {
    accessToken: sessionStorage.getItem("accessToken"),
    // accessToken: this.$session.get("accessToken"),
    user:  sessionStorage.getItem("user"),
    email: sessionStorage.getItem("email"),
    phone: sessionStorage.getItem("phone"),
    auth: sessionStorage.getItem("auth"),
    personnel: 0, // 인원
    budget: 0, // 예산 
    tokenType: '',
    budgetlist : [],
    budgetListBar: [
      { category : '정육/계란류',
        price: 0,
        className: 'progress-bar bg-danger'
      }, {
        category : '생수/음료',
        price: 0,
        className: 'progress-bar bg-primary'
      }, {
        category : '채소',
        price: 0,
        className: 'progress-bar bg-success'
      }, {
        category : '라면',
        price: 0,
        className: 'progress-bar bg-warning'
      }],

    budgetListBarETC: [
      {
        category : '수산물/해산물',
        price: 0,
        className: 'progress-bar bg-primary'
      }, {
        category : '쌀/잡곡',
        price: 0,
        className: 'progress-bar bg-secondary'

      }, {
        category : '즉석식품',
        price: 0,
        className: 'progress-bar bg-dark'

      }, {
        category : '과일',
        price: 0,
        className: 'progress-bar bg-success'

      }, {
        category : '스낵',
        price: 0,
        className: 'progress-bar bg-warning'
      }, {
        category : '견과/건해산물',
        price: 0,
        className: 'progress-bar bg-info'
      }
    ],
    ETC: ['수산물/해산물', '쌀/잡곡', '즉석식품', '과일', '스낵', '견과/건해산물'],
    recommendDict: {
      water: '생수/음료',
      noodle : '라면',
      rice: '쌀/잡곡',
      instant: '즉석식품',
      nuts: '견과/건해산물',
      vegetable : '채소',
      seafood: '수산물/해산물',
      fruits: '과일',
      snack: '스낵',
      meat: '정육/계란류'
    },
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
    },
    isAdmin: function(state) {
      return state.auth === "3" ? true : false
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
        const barIdx = state.budgetListBarETC.findIndex(budget => {
          return budget.category === product.category
        })
        state.budgetListBarETC[barIdx].price -= (product.price * product.quantity)
        if (state.budgetListBarETC[barIdx].price <= 0) {
          state.budgetListBarETC.splice(barIdx, 0)
        }
        state.etcTotal -= (product.price * product.quantity)

      } else {
        console.log(`${product.category}에서 ${product.price * product.quantity}원 차감`)
        const barIdx = state.budgetListBar.findIndex(budget => {
          return budget.category === product.category
        })
        state.budgetListBar[barIdx].price -= (product.price * product.quantity)
        if (state.budgetListBar[barIdx].price <= 0) {
          state.budgetListBar.splice(barIdx, 0)
        }
        state.mainTotal -= (product.price* product.quantity)
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
        state.budgetlist[idx].quantity += Number(product.quantity)
        state.budgetlist[idx].pro_price += (product.quantity * product.price)
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

 
        state.budgetListBarETC[barIdx].price += (product.price * product.quantity)
          // state.etcTotal += (product.price*product.quantity);

      } else {
        const barIdx = state.budgetListBar.findIndex( budget => {
          return budget.category === product.category
        })

        // const className = state.budgetListBarClass.find(budget => {
        //   return budget.
        // })
        // console.log(state.budgetListBarClass['정육/계란류'])
  
            state.budgetListBar[barIdx].price += (product.price * product.quantity)
        // state.mainTotal += (product.price*product.quantity);
      }

      console.log(state.budgetListBar)
      console.log(state.budgetListBarETC)
    },

    changeQuantity : function(state, changeInfo) {
      console.log('changeQuantity')
      console.log(changeInfo)
      if (changeInfo.isETC === false) { // 메인일때
        const barIdx = state.budgetListBar.findIndex(budget => {
          return budget.category === changeInfo.category
        })
        if (changeInfo.ope === 'p') {
          state.mainTotal += changeInfo.price
          console.log(state.budgetListBar[barIdx])
          state.budgetListBar[barIdx].price += changeInfo.price
        } else {
          state.mainTotal -= changeInfo.price
          state.budgetListBar[barIdx].price -= changeInfo.price
        }

        // if (state.budgetListBar[barIdx].price === 0) {
        //   state.budgetListBar.splice(barIdx, 0)
        // }
      } else { // 기타일때
        const barIdx = state.budgetListBarETC.findIndex(budget => {
          return budget.category === changeInfo.category
        })
        if (changeInfo.ope === 'p') {
          state.etcTotal += changeInfo.price
          state.budgetListBarETC[barIdx].price += changeInfo.price
        } else {
          state.etcTotal -= changeInfo.price
          state.budgetListBarETC[barIdx].price -= changeInfo.price
        }
        // if (state.budgetListBarETC[barIdx].price === 0) {
        //   state.budgetListBarETC.splice(barIdx, 0)
        // }
        
      }

      const idx = changeInfo.idx

      if (changeInfo.ope == 'p') {
        state.budgetlist[idx].quantity = Number(changeInfo.changeQuantity)
        state.budgetlist[idx].pro_price += changeInfo.price
      } else {
        state.budgetlist[idx].quantity = Number(changeInfo.changeQuantity)
        state.budgetlist[idx].pro_price -= changeInfo.price
      }

      const category = changeInfo.category
      // if (state.ETC.includes(category)) {

      // }


    },

    allClear: function(state) {
      console.log('allClear')
      state.budgetlist = [];
      for (let budget of state.budgetListBar) {
        budget.price = 0
      }
      for (let budget of state.budgetListBarETC) {
        budget.price = 0
      }
      state.mainTotal = 0;
      state.etcTotal = 0;
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
  },

  deleteBudgetBarAsync: function(options, product) {
    options.commit('deleteBudgetBar', product)
  },

  changeQuantityAsync: function(options, changeInfo) {
    console.log('changeQuantityAsync')
    console.log(changeInfo)
    options.commit('changeQuantity', changeInfo)
  },

  allClearAsync: function(options) {
    console.log('allClearAsync')
    options.commit('allClear')
  }
}
})