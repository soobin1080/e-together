import Vue from 'vue'
import Router from 'vue-router'
import MainPage from './views/MainPage.vue'
import PostPage from './views/PostPage.vue'
import PortfolioPage from './views/PortfolioPage.vue'
import LoginPage from './views/LoginPage.vue'
import SignUpPage from './views/SignUpPage.vue'
import MartPage from './views/MartPage.vue'
import TeamPage from './views/TeamPage.vue'
import UserInfoPage from './views/UserInfoPage.vue'
import MyBudgetListPage from './views/MyBudgetListPage.vue'

Vue.use(Router)

export default new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
		{
			path: '/',
			name: 'home',
			component: MainPage
		},
		{
			path: '/post',
			name: 'post',
			component: PostPage
		},
		{
			path: '/portfolio',
			name: 'portfolio',
			component: PortfolioPage
		},
		{

			path: '/mart',
			name: 'mart',
			component: MartPage
		},		
		{
			path: '/team',
			name: 'team',
			component: TeamPage
		},	
		{
			path: '/signup',
			name: 'signup',
			component: SignUpPage
		},
		{
			path: '/userinfo',
			name: 'userinfo',
			component: UserInfoPage

		},
		{
			path: '/mylist',
			name: 'mylist',
			component: MyBudgetListPage
		},
  ]
})
