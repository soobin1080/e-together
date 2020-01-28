<template>
<div>
  <LoginModal></LoginModal>
<v-navigation-drawer v-model="sidebar" fixed temporary>

    <v-list>
      <h5 class="ml-5">hello</h5>
      <v-list-item
        v-for="item in menuItems"
        :key="item.title"
        :to="item.path">
        <v-list-item-action>           
        </v-list-item-action>
        <v-list-item-content>{{ item.title }}</v-list-item-content>
      </v-list-item>
      <v-list-item
        v-if="this.$store.state.user" 
        @click="$modal.show('login-modal')" 
        ><v-list-item-action>           
        </v-list-item-action>
        <v-list-item-content class="text-center">Logout</v-list-item-content>
      </v-list-item>
      <v-list-item
        v-else 
        @click="$modal.show('login-modal')" 
        ><v-list-item-action>           
        </v-list-item-action>
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
          <strong>{{ appTitle }}</strong>
        </router-link>
      </v-toolbar-title>
      <v-spacer></v-spacer>
      <v-toolbar-items class="hidden-xs-only">
        <v-btn
          text
          v-for="item in menuItems"
          :key="item.title"
          :to="item.path">         
          {{ item.title }}
        </v-btn>
        <v-btn text @click="$modal.show('login-modal')">
          Login
        </v-btn>
      </v-toolbar-items>
    </v-app-bar>
</div>
</template>

<script>
import LoginModal from '../components/LoginModal'
export default {
  name: "Header",
  components: {
    LoginModal,
  },
  data(){
    return {
      appTitle: 'Blog',
      sidebar: false,
      menuItems: [
          { title: 'Home', path: '/'},
          { title: 'Post', path: '/post'},
          { title: 'Portfolio', path: '/portfolio'},
          {title: 'UserInfo', path: '/userinfo'},
     ]
    }
  }
};
</script>
