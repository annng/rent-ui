package com.rent.app.injection

import com.rent.app.ui.account.edit.password.EditPasswordActivity
import com.rent.app.ui.account.edit.password.EditPasswordModule
import com.rent.app.ui.account.edit.profile.EditProfileActivity
import com.rent.app.ui.account.edit.profile.EditProfileModule
import com.rent.app.ui.account.wishlist.WishlistActivity
import com.rent.app.ui.account.wishlist.WishlistModule
import com.rent.app.ui.auth.login.LoginActivity
import com.rent.app.ui.auth.login.LoginModule
import com.rent.app.ui.auth.register.RegisterActivity
import com.rent.app.ui.auth.register.RegisterModule
import com.rent.app.ui.home.message.MessageActivity
import com.rent.app.ui.home.message.MessageModule
import com.rent.app.ui.home.notification.NotificationActivity
import com.rent.app.ui.home.notification.NotificationModule
import com.rent.app.ui.home.review.ReviewActivity
import com.rent.app.ui.home.review.ReviewModule
import com.rent.app.ui.main.MainActivity
import com.rent.app.ui.main.MainModule
import com.rent.app.ui.main.child.account.AccountFragment
import com.rent.app.ui.main.child.account.AccountModule
import com.rent.app.ui.main.child.cart.CartFragment
import com.rent.app.ui.main.child.cart.CartModule
import com.rent.app.ui.main.child.home.HomeFragment
import com.rent.app.ui.main.child.home.HomeModule
import com.rent.app.ui.main.child.order.OrderFragment
import com.rent.app.ui.main.child.order.OrderModule
import com.rent.app.ui.order.OrderDetailActivity
import com.rent.app.ui.order.OrderDetailModule
import com.rent.app.ui.order.child.OrderDetailProductFragment
import com.rent.app.ui.order.child.OrderDetailShippingFragment
import com.rent.app.ui.order.child.OrderDetailStatusFragment
import com.rent.app.ui.product.detail.ProductDetailActivity
import com.rent.app.ui.product.detail.ProductDetailModule
import com.rent.app.ui.product.list.ProductListActivity
import com.rent.app.ui.product.list.ProductListModule
import com.rent.app.ui.product.search.ProductSearchActivity
import com.rent.app.ui.product.search.ProductSearchModule
import com.rent.app.ui.shipping.ShippingActivity
import com.rent.app.ui.shipping.ShippingModule
import com.rent.app.ui.splash.SplashActivity
import com.rent.app.ui.splash.SplashModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    //region [SPLASH]
    @ContributesAndroidInjector(modules = [SplashModule::class])
    abstract fun bindSplashActivity(): SplashActivity
    //endregion

    //region [AUTH]
    @ContributesAndroidInjector(modules = [LoginModule::class])
    abstract fun bindLoginActivity(): LoginActivity

    @ContributesAndroidInjector(modules = [RegisterModule::class])
    abstract fun bindRegisterActivity(): RegisterActivity
    //endregion

    //region [MAIN]
    @ContributesAndroidInjector(modules = [MainModule::class])
    abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [HomeModule::class])
    abstract fun bindHomeFragment(): HomeFragment

    @ContributesAndroidInjector(modules = [OrderModule::class])
    abstract fun bindOrderFragment(): OrderFragment

    @ContributesAndroidInjector(modules = [CartModule::class])
    abstract fun bindCartFragment(): CartFragment

    @ContributesAndroidInjector(modules = [AccountModule::class])
    abstract fun bindAccountFragment(): AccountFragment
    //endregion


    //region [PRODUCT]
    @ContributesAndroidInjector(modules = [ProductDetailModule::class])
    abstract fun bindProductDetailActivity(): ProductDetailActivity

    @ContributesAndroidInjector(modules = [ProductSearchModule::class])
    abstract fun bindProductSearchActivity(): ProductSearchActivity

    @ContributesAndroidInjector(modules = [ProductListModule::class])
    abstract fun bindProductListActivity(): ProductListActivity
    //endregion

    //region [HOME]
    @ContributesAndroidInjector(modules = [MessageModule::class])
    abstract fun bindMessageActivity(): MessageActivity

    @ContributesAndroidInjector(modules = [NotificationModule::class])
    abstract fun bindNotificationActivity(): NotificationActivity

    @ContributesAndroidInjector(modules = [ReviewModule::class])
    abstract fun bindReviewActivity(): ReviewActivity
    //endregion

    //region [ORDER]
    @ContributesAndroidInjector(modules = [OrderDetailModule::class])
    abstract fun bindOrderDetailActivity(): OrderDetailActivity

    @ContributesAndroidInjector(modules = [OrderDetailModule::class])
    abstract fun bindOrderDetailProductFragment(): OrderDetailProductFragment

    @ContributesAndroidInjector(modules = [OrderDetailModule::class])
    abstract fun bindOrderDetailStatusFragment(): OrderDetailStatusFragment

    @ContributesAndroidInjector(modules = [OrderDetailModule::class])
    abstract fun bindOrderDetailShippingFragment(): OrderDetailShippingFragment

    @ContributesAndroidInjector(modules = [ShippingModule::class])
    abstract fun bindShippingActivity(): ShippingActivity
    //endregion

    //region [ACCOUNT]
    @ContributesAndroidInjector(modules = [WishlistModule::class])
    abstract fun bindWishlistActivity(): WishlistActivity

    @ContributesAndroidInjector(modules = [EditPasswordModule::class])
    abstract fun bindEditPasswordActivity(): EditPasswordActivity

    @ContributesAndroidInjector(modules = [EditProfileModule::class])
    abstract fun bindEditProfileActivity(): EditProfileActivity

    //endregion
}