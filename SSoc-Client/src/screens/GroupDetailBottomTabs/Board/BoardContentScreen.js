import { BoardContentList } from './BoardContentList'

export const BoardAllContentScreen = (props) => {
  return (
    <BoardContentList
      groupMemberRole={props.route.params.groupMemberRole}
      board={props.route.params.board}
    />
  )
}

export const BoardFreeContentScreen = (props) => {
  return (
    <BoardContentList
      groupMemberRole={props.route.params.groupMemberRole}
      board={props.route.params.board}
    />
  )
}

export const BoardNoticeContentScreen = (props) => {
  return (
    <BoardContentList
      groupMemberRole={props.route.params.groupMemberRole}
      board={props.route.params.board}
    />
  )
}

export const BoardSuggestContentScreen = (props) => {
  return (
    <BoardContentList
      groupMemberRole={props.route.params.groupMemberRole}
      board={props.route.params.board}
    />
  )
}
