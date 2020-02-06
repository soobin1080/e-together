<template>
  <div>
    <LoginModal @checkLogIn="loggedIn"></LoginModal>
    <v-navigation-drawer v-model="sidebar" fixed temporary>
      <v-list>
        <h5 class="ml-5">hello</h5>

        <v-list-item v-if="username" @click="pwdCheck">
          {{username}}
          <!-- <v-list-item-action></v-list-item-action> -->
            <!-- <v-list-item-content class="text-center" v-model="username">{{username}}</v-list-item-content> -->
        </v-list-item>
        <v-list-item v-for="item in menuItems" :key="item.title" :to="item.path">
          <v-list-item-action></v-list-item-action>
          <v-list-item-content>{{ item.title }}</v-list-item-content>
        </v-list-item>
        <v-list-item v-if="username" @click="$modal.show('login-modal')">
          <v-list-item-action></v-list-item-action>
          <v-list-item-content class="text-center">Logout</v-list-item-content>
        </v-list-item>
        <v-list-item v-else @click="$modal.show('login-modal')">
          <v-list-item-action></v-list-item-action>
          <v-list-item-content class="text-center">Login</v-list-item-content>
        </v-list-item>
      </v-list>
    </v-navigation-drawer>

    <v-app-bar app>
      <span class="hidden-sm-and-up">
        <v-app-bar-nav-icon @click="sidebar = !sidebar">
          <v-icon>menu</v-icon>
        </v-app-bar-nav-icon>
      </span>
      <v-toolbar-title>
        <router-link to="/" tag="span" style="cursor: pointer">
          <v-img :src="getImgUrl('etogether.png')" width="100px" />
        </router-link>
      </v-toolbar-title>
      <v-spacer></v-spacer>
      <v-toolbar-items class="hidden-xs-only">
        <v-btn text @click="pwdCheck">{{ username }}</v-btn>
        <v-btn text v-for="item in menuItems" :key="item.title" :to="item.path">{{ item.title }}</v-btn>

        <v-btn v-if="username" text @click="">Logout</v-btn>
        <v-btn v-else text @click="$modal.show('login-modal')">Login</v-btn>
        <v-btn v-if="!username" text to="/findpwd">비밀번호 찾기</v-btn>
      </v-toolbar-items>
    </v-app-bar>
    
  </div>
</template>

<script>
import LoginModal from "../components/LoginModal";
export default {
  name: "Header",
  components: {
    LoginModal
  },
   data() {
    return {
      appTitle: "Blog",
      sidebar: false,
      menuItems: [
        { title: "Home", path: "/" },
        { title: "Mart", path: "/product" },
        { title: "My Budget", path: "/mybudget" },
        { title: "Review", path: "/review" },
        { title: "UserInfo", path: "/userinfo" },
        
      ],
      username : "",
      isLoggedIn: false,
    };
  },

  methods: {
    getImgUrl(img) {
      return require("../assets/" + img);
    },
    pwdCheck() {
      console.log('pwdCheck')
      this.$router.push('/pwdcheck')
    },
    loggedIn() {
      console.log('emit!')
      this.getUserName()
    },
    getUserName(){
      console.log('getUserName')
      console.log(localStorage.getItem('user')) 
      this.username = localStorage.getItem('user')
      this.isLoggedIn = this.$store.getters.isLoggedIn
    },
  },
  computed: {
    setUserName() {
      console.log('setUserName')
      // this.username = this.$store.state.user
      console.log(this.username)
      return this.username
    },

    checkLoggedIn() {
      return this.isLoggedIn
    }
  },
  mounted() {
    this.getUserName()
    //this.checkLoggedIn()
  }
};
</script>
