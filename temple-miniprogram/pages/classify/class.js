// pages/classify/class.js
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    active: 0,
    goodsClassArray: {},
    goodsArray: {}
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that = this
    that.index = options.index
    that.id = options.id
    that.name = options.name
    console.log("index: " + that.index + " id: " + that.id + " name: " + that.name)
    this.loadGoodsClass()
    this.loadGoods(that.index, that.id)
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
  loadGoodsClass: function() {
    var that = this
    console.log("class id: " + that.id + " name: " + that.name)
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
  loadGoods: function (index, goodsClassId) {
    var that = this
    if (index == undefined && goodsClassId == undefined) {
      index = 0
    }
    console.log("goods index: " + index + " classId: " + goodsClassId)
    var url = app.globalData.url + '/goods/list'
    wx.request({
      url: url,
      data: {
        goodsClassId: goodsClassId
      },
      method: 'POST',
      success: function (result) {
        console.log(result)
        var resultArray = result.data.map(item => {
          item.image = app.globalData.url + item.image
        })
        console.log(resultArray)
        that.setData({
          active: index,
          goodsArray: result.data
        })
      }
    });
  },
  goodsClassAction: function (e) {
    console.log("class action id: " + e.currentTarget.dataset.id + " name: " + e.currentTarget.dataset.name)
    this.loadGoods(e.currentTarget.dataset.index, e.currentTarget.dataset.id)
  },
  goodsAction: function (e) {
    var id = e.currentTarget.dataset.id
    var name = e.currentTarget.dataset.name
    console.log("goods action id: " + id + " name: " + name)
    wx.navigateTo({
      url: '/pages/detail/detail?id=' + id + '&name=' + name
    })
  }
})