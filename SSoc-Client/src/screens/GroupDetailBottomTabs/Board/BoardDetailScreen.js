import { View } from 'react-native'
import * as Color from '../../../components/Colors/colors'
import { BoardDetailTopTabs } from './BoardDetatilTopTabs'

export const BoardDetailScreen = (props) => {
  const groupId = props.route.params.groupId
  const groupMemberRole = props.route.params.groupMemberRole
  console.log('BoardDetailScreen.js (groupId) : ', groupId)
  console.log('BoardDetailScreen.js (groupMemberRole) : ', groupMemberRole)
  return (
    <View style={{ flex: 1, backgroundColor: Color.WHITE }}>
      <BoardDetailTopTabs
        groupId={groupId}
        groupMemberRole={groupMemberRole}
      />
    </View>
  )
}
