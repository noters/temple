// pages/detail.js
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    active: -1,
    goods: {},
    callback: false
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that = this
    that.id = options.id
    this.loadGoods(that.id)
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
    if (this.data.callback) {
      this.buyAction()
    }
  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  },
  loadGoods: function (goodsId) {
    var that = this
    var url = app.globalData.url + '/goodsDetail/list'
    wx.request({
      url: url,
      data: {
        id: goodsId
      },
      method: 'POST',
      success: function (result) {
        console.log(result)
        result.data.image = app.globalData.url + result.data.image
        result.data.goodsDetailList.map(item => {
          item.image = app.globalData.url + item.image
        })
        console.log(result)
        that.setData({
          goods: result.data
        })
      }
    });
  },
  itemAction: function (e) {
    var that = this
    var index = e.currentTarget.dataset.index
    that.setData({
      active: index
    })
  },
  homeAction: function () {
    wx.switchTab({
      url: '/pages/home/home'
    })
  },
  buyAction: function (e) {
    var that = this
    var index = that.data.active
    if (index < 0) {
      wx.showToast({
          title: "请先选择项目",
          icon: "none",
          image: "",
          duration: 1000,
          mask: false,
          success: function () {},
          fail: function () {},
          complete: function () {}
      })
      return
    }
    var id = that.data.goods.goodsItemList[index].id
    var goods = that.data.goods.goodsItemList[index].goods
    console.log("buy action index: " + index + " id: " + id + " goods: " + goods)
    console.log(that.data.goods)
    console.log(app.globalData)
    // var appid = app.globalData.appid
    // var openid = app.globalData.openid
    // var secret = app.globalData.secret
    if (!app.globalData.userInfo) {
      wx.navigateTo({
          url: '/pages/allow/allow'
      })
      return
    }
    var nickName = app.globalData.userInfo.nickName
    var gender = app.globalData.userInfo.gender
    console.log("nickName: " + nickName + " gender: " + gender)
    var url = app.globalData.url + '/trade/create'
    wx.request({
      url: url,
      data: {
        appid: app.globalData.appid,
        secret: app.globalData.secret,
        openid: app.globalData.openid,
        nickName: nickName,
        gender: gender,
        goodsId: goods,
        goodsItemId: id
      },
      method: 'POST',
      // header: {},
      success: function (result) {
        console.log(result)
        if (result.data.data) {
          wx.redirectTo({
              url: '/pages/complete/complete'
          })
          // wx.requestPayment({
          //   "timeStamp": result.data.data.timeStamp,
          //   "nonceStr": result.data.data.nonceStr,
          //   "package": result.data.data.package,
          //   "signType": "MD5",
          //   "paySign": result.data.data.paySign,
          //   "success":function(res){
          //     wx.navigateBack({
          //         url: '/pages/complete/complete'
          //     })
          //   },
          //   "fail":function(res){},
          //   "complete":function(res){}
          //   })
        }
      }
    });
  }
})