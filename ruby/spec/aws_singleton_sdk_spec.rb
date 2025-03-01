require 'aws_singleton_sdk'
require 'spec_helper'

RSpec.describe AWSSingletonSDK do
  it 'returns the same instance' do
    instance1 = AWSSingletonSDK.instance
    instance2 = AWSSingletonSDK.instance
    expect(instance1).to be(instance2)
  end

  it 'initializes the AWS Step Functions client' do
    instance = AWSSingletonSDK.instance
    expect(instance.client).to be_an_instance_of(Aws::States::Client)
  end

  it 'uses the correct AWS region' do
    instance = AWSSingletonSDK.instance
    expect(instance.client.config.region).to eq('us-east-1')
  end
end