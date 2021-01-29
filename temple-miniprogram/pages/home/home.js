// pages/home/home.js
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    bannerArray: {},
    goodsClassArray: {},
    goodsArray: {}
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.loadBanner()
    this.loadGoodsClass()
    this.loadGoods()
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
  loadBanner: function () {
    var that = this
    var url = app.globalData.url + '/banner/list'
    wx.request({
      url: url,
      data: {},
      method: 'POST',
      success: function (result) {
        console.log(result)
        var resultArray = result.data.map(item => {
          item.image = app.globalData.url + item.image
        })
        console.log(resultArray)
        that.setData({
          bannerArray: result.data
        })
      }
    });
  },
  loadGoodsClass: function () {
    var that = this
    var url = app.globalData.url + '/goodsClass/list'
    wx.request({
      url: url,
      data: {},
      method: 'POST',
      success: function (result) {
        console.log(result)
        //that.data.goodsClassArray = result.data
        var resultArray = result.data.map(item => {
          item.image = app.globalData.url + item.image
        })
        console.log(resultArray)
        that.setData({
          goodsClassArray: result.data
        })
      }
    });
  },
  loadGoods: function () {
    var that = this
    var url = app.globalData.url + '/goods/listPage'
    wx.request({
      url: url,
      data: {
        "current":1,
        "size": 10
      },
      method: 'POST',
      success: function (result) {
        console.log(result)
        var resultArray = result.data.map(item => {
          item.image = app.globalData.url + item.image
        })
        console.log(resultArray)
        that.setData({
          goodsArray: result.data
        })
      }
    });
  },
  goodsClassAction: function (e) {
    var index = e.currentTarget.dataset.index
    var id = e.currentTarget.dataset.id
    var name = e.currentTarget.dataset.name
    console.log("index: " + index + " id: " + id + " name: " + name)

  }
})