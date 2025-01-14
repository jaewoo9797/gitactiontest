package com.sprint.mission.discodeit.entity.user.entity;

import static com.sprint.mission.discodeit.common.error.ErrorMessage.USER_NOT_PARTICIPATED_CHANNEL;

import com.sprint.mission.discodeit.common.error.user.UserException;
import com.sprint.mission.discodeit.entity.channel.Channel;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class ParticipatedChannel {

    private final Map<UUID, Channel> participatedChannels;

    private ParticipatedChannel(Map<UUID, Channel> channels) {
        this.participatedChannels = channels;
    }

    public static ParticipatedChannel newDefault() {
        return new ParticipatedChannel(new HashMap<>());
    }

    public Channel createChannel(String channelName, User user) {
        var createdChannel = Channel.createFromChannelNameAndUser(channelName, user);
        participatedChannels.put(createdChannel.getId(), createdChannel);

        return createdChannel;
    }

    public List<Channel> findAllChannels() {
        var participatedChannels =
                this.participatedChannels.values()
                .stream()
                .toList();

        return Collections.unmodifiableList(participatedChannels);
    }

    public Optional<Channel> findById(UUID channelId) {
        var foundChannel = participatedChannels.get(channelId);
        return Optional.ofNullable(foundChannel);
    }

    // TODO 같은 이름을 가진 채널이 여러 개 있을 수 있음. 리스트로 반환 고려해야하지 않을까?
    public Optional<Channel> findByName(String name) {
        var foundChannelByName = participatedChannels.values()
                .stream()
                .filter(channel -> channel.isEqualFromNameAndNotUnregistered(name))
                .findFirst();

        return foundChannelByName;
    }

    public Channel changeChannelNameOrThrow(UUID channelId, String newName, User user) {
        var foundChannel = findById(channelId)
                .orElseThrow(() ->
                        UserException.errorMessageAndId(USER_NOT_PARTICIPATED_CHANNEL, channelId.toString())
                );

        foundChannel.changeName(newName, user);
        participatedChannels.put(foundChannel.getId(), foundChannel);

        return foundChannel;
    }

    // 채널 삭제
    public void deleteChannelById(UUID channelId) {
        findById(channelId).ifPresent(Channel::deleteChannel);
    }
}
