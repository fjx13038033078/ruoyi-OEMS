import request from '@/utils/request'

// 查询所有公告列表
export function listAllAnnouncements(query) {
  return request({
    url: '/oversea/announcement/listAll',
    method: 'get',
    params: query
  })
}

// 查询公告详细信息
export function getAnnouncementById(announcementId) {
  return request({
    url: '/oversea/announcement/detail',
    method: 'get',
    params: { announcementId }
  })
}

// 添加公告
export function addAnnouncement(data) {
  return request({
    url: '/oversea/announcement/add',
    method: 'post',
    data: data
  })
}

// 更新公告信息
export function updateAnnouncement(data) {
  return request({
    url: '/oversea/announcement/update',
    method: 'post',
    data: data
  })
}

// 删除公告
export function deleteAnnouncement(announcementId) {
  return request({
    url: '/oversea/announcement/delete',
    method: 'get',
    params: { announcementId }
  })
}
