// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: cat/ogasoft/protocolizer/protoc/File.proto

package cat.ogasoft.protocolizer.messages;

public final class File {
  private File() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  public interface SearchRequestOrBuilder extends
      // @@protoc_insertion_point(interface_extends:SearchRequest)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>required string query = 1;</code>
     */
    boolean hasQuery();
    /**
     * <code>required string query = 1;</code>
     */
    java.lang.String getQuery();
    /**
     * <code>required string query = 1;</code>
     */
    com.google.protobuf.ByteString
        getQueryBytes();

    /**
     * <code>required int32 page_number = 2;</code>
     */
    boolean hasPageNumber();
    /**
     * <code>required int32 page_number = 2;</code>
     */
    int getPageNumber();

    /**
     * <code>required int32 result_per_page = 3;</code>
     */
    boolean hasResultPerPage();
    /**
     * <code>required int32 result_per_page = 3;</code>
     */
    int getResultPerPage();

    /**
     * <code>required .SearchRequest.Corpus corpussdfa = 4;</code>
     */
    boolean hasCorpussdfa();
    /**
     * <code>required .SearchRequest.Corpus corpussdfa = 4;</code>
     */
    cat.ogasoft.protocolizer.messages.File.SearchRequest.Corpus getCorpussdfa();
  }
  /**
   * Protobuf type {@code SearchRequest}
   */
  public  static final class SearchRequest extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:SearchRequest)
      SearchRequestOrBuilder {
    // Use SearchRequest.newBuilder() to construct.
    private SearchRequest(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private SearchRequest() {
      query_ = "";
      pageNumber_ = 0;
      resultPerPage_ = 0;
      corpussdfa_ = 0;
    }

    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
    getUnknownFields() {
      return this.unknownFields;
    }
    private SearchRequest(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      this();
      int mutable_bitField0_ = 0;
      com.google.protobuf.UnknownFieldSet.Builder unknownFields =
          com.google.protobuf.UnknownFieldSet.newBuilder();
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            default: {
              if (!parseUnknownField(input, unknownFields,
                                     extensionRegistry, tag)) {
                done = true;
              }
              break;
            }
            case 10: {
              com.google.protobuf.ByteString bs = input.readBytes();
              bitField0_ |= 0x00000001;
              query_ = bs;
              break;
            }
            case 16: {
              bitField0_ |= 0x00000002;
              pageNumber_ = input.readInt32();
              break;
            }
            case 24: {
              bitField0_ |= 0x00000004;
              resultPerPage_ = input.readInt32();
              break;
            }
            case 32: {
              int rawValue = input.readEnum();
              cat.ogasoft.protocolizer.messages.File.SearchRequest.Corpus value = cat.ogasoft.protocolizer.messages.File.SearchRequest.Corpus.valueOf(rawValue);
              if (value == null) {
                unknownFields.mergeVarintField(4, rawValue);
              } else {
                bitField0_ |= 0x00000008;
                corpussdfa_ = rawValue;
              }
              break;
            }
          }
        }
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(this);
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(
            e).setUnfinishedMessage(this);
      } finally {
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return cat.ogasoft.protocolizer.messages.File.internal_static_SearchRequest_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return cat.ogasoft.protocolizer.messages.File.internal_static_SearchRequest_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              cat.ogasoft.protocolizer.messages.File.SearchRequest.class, cat.ogasoft.protocolizer.messages.File.SearchRequest.Builder.class);
    }

    /**
     * Protobuf enum {@code SearchRequest.Corpus}
     */
    public enum Corpus
        implements com.google.protobuf.ProtocolMessageEnum {
      /**
       * <code>UNIVERSAL = 0;</code>
       */
      UNIVERSAL(0),
      /**
       * <code>WEB = 1;</code>
       */
      WEB(1),
      /**
       * <code>IMAGES = 2;</code>
       */
      IMAGES(2),
      /**
       * <code>LOCAL = 3;</code>
       */
      LOCAL(3),
      /**
       * <code>NEWS = 4;</code>
       */
      NEWS(4),
      /**
       * <code>PRODUCTS = 5;</code>
       */
      PRODUCTS(5),
      /**
       * <code>VIDEO = 6;</code>
       */
      VIDEO(6),
      ;

      /**
       * <code>UNIVERSAL = 0;</code>
       */
      public static final int UNIVERSAL_VALUE = 0;
      /**
       * <code>WEB = 1;</code>
       */
      public static final int WEB_VALUE = 1;
      /**
       * <code>IMAGES = 2;</code>
       */
      public static final int IMAGES_VALUE = 2;
      /**
       * <code>LOCAL = 3;</code>
       */
      public static final int LOCAL_VALUE = 3;
      /**
       * <code>NEWS = 4;</code>
       */
      public static final int NEWS_VALUE = 4;
      /**
       * <code>PRODUCTS = 5;</code>
       */
      public static final int PRODUCTS_VALUE = 5;
      /**
       * <code>VIDEO = 6;</code>
       */
      public static final int VIDEO_VALUE = 6;


      public final int getNumber() {
        return value;
      }

      /**
       * @deprecated Use {@link #forNumber(int)} instead.
       */
      @java.lang.Deprecated
      public static Corpus valueOf(int value) {
        return forNumber(value);
      }

      public static Corpus forNumber(int value) {
        switch (value) {
          case 0: return UNIVERSAL;
          case 1: return WEB;
          case 2: return IMAGES;
          case 3: return LOCAL;
          case 4: return NEWS;
          case 5: return PRODUCTS;
          case 6: return VIDEO;
          default: return null;
        }
      }

      public static com.google.protobuf.Internal.EnumLiteMap<Corpus>
          internalGetValueMap() {
        return internalValueMap;
      }
      private static final com.google.protobuf.Internal.EnumLiteMap<
          Corpus> internalValueMap =
            new com.google.protobuf.Internal.EnumLiteMap<Corpus>() {
              public Corpus findValueByNumber(int number) {
                return Corpus.forNumber(number);
              }
            };

      public final com.google.protobuf.Descriptors.EnumValueDescriptor
          getValueDescriptor() {
        return getDescriptor().getValues().get(ordinal());
      }
      public final com.google.protobuf.Descriptors.EnumDescriptor
          getDescriptorForType() {
        return getDescriptor();
      }
      public static final com.google.protobuf.Descriptors.EnumDescriptor
          getDescriptor() {
        return cat.ogasoft.protocolizer.messages.File.SearchRequest.getDescriptor().getEnumTypes().get(0);
      }

      private static final Corpus[] VALUES = values();

      public static Corpus valueOf(
          com.google.protobuf.Descriptors.EnumValueDescriptor desc) {
        if (desc.getType() != getDescriptor()) {
          throw new java.lang.IllegalArgumentException(
            "EnumValueDescriptor is not for this type.");
        }
        return VALUES[desc.getIndex()];
      }

      private final int value;

      private Corpus(int value) {
        this.value = value;
      }

      // @@protoc_insertion_point(enum_scope:SearchRequest.Corpus)
    }

    private int bitField0_;
    public static final int QUERY_FIELD_NUMBER = 1;
    private volatile java.lang.Object query_;
    /**
     * <code>required string query = 1;</code>
     */
    public boolean hasQuery() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>required string query = 1;</code>
     */
    public java.lang.String getQuery() {
      java.lang.Object ref = query_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          query_ = s;
        }
        return s;
      }
    }
    /**
     * <code>required string query = 1;</code>
     */
    public com.google.protobuf.ByteString
        getQueryBytes() {
      java.lang.Object ref = query_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        query_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    public static final int PAGE_NUMBER_FIELD_NUMBER = 2;
    private int pageNumber_;
    /**
     * <code>required int32 page_number = 2;</code>
     */
    public boolean hasPageNumber() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <code>required int32 page_number = 2;</code>
     */
    public int getPageNumber() {
      return pageNumber_;
    }

    public static final int RESULT_PER_PAGE_FIELD_NUMBER = 3;
    private int resultPerPage_;
    /**
     * <code>required int32 result_per_page = 3;</code>
     */
    public boolean hasResultPerPage() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    /**
     * <code>required int32 result_per_page = 3;</code>
     */
    public int getResultPerPage() {
      return resultPerPage_;
    }

    public static final int CORPUSSDFA_FIELD_NUMBER = 4;
    private int corpussdfa_;
    /**
     * <code>required .SearchRequest.Corpus corpussdfa = 4;</code>
     */
    public boolean hasCorpussdfa() {
      return ((bitField0_ & 0x00000008) == 0x00000008);
    }
    /**
     * <code>required .SearchRequest.Corpus corpussdfa = 4;</code>
     */
    public cat.ogasoft.protocolizer.messages.File.SearchRequest.Corpus getCorpussdfa() {
      cat.ogasoft.protocolizer.messages.File.SearchRequest.Corpus result = cat.ogasoft.protocolizer.messages.File.SearchRequest.Corpus.valueOf(corpussdfa_);
      return result == null ? cat.ogasoft.protocolizer.messages.File.SearchRequest.Corpus.UNIVERSAL : result;
    }

    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized == 1) return true;
      if (isInitialized == 0) return false;

      if (!hasQuery()) {
        memoizedIsInitialized = 0;
        return false;
      }
      if (!hasPageNumber()) {
        memoizedIsInitialized = 0;
        return false;
      }
      if (!hasResultPerPage()) {
        memoizedIsInitialized = 0;
        return false;
      }
      if (!hasCorpussdfa()) {
        memoizedIsInitialized = 0;
        return false;
      }
      memoizedIsInitialized = 1;
      return true;
    }

    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 1, query_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        output.writeInt32(2, pageNumber_);
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        output.writeInt32(3, resultPerPage_);
      }
      if (((bitField0_ & 0x00000008) == 0x00000008)) {
        output.writeEnum(4, corpussdfa_);
      }
      unknownFields.writeTo(output);
    }

    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, query_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(2, pageNumber_);
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(3, resultPerPage_);
      }
      if (((bitField0_ & 0x00000008) == 0x00000008)) {
        size += com.google.protobuf.CodedOutputStream
          .computeEnumSize(4, corpussdfa_);
      }
      size += unknownFields.getSerializedSize();
      memoizedSize = size;
      return size;
    }

    private static final long serialVersionUID = 0L;
    @java.lang.Override
    public boolean equals(final java.lang.Object obj) {
      if (obj == this) {
       return true;
      }
      if (!(obj instanceof cat.ogasoft.protocolizer.messages.File.SearchRequest)) {
        return super.equals(obj);
      }
      cat.ogasoft.protocolizer.messages.File.SearchRequest other = (cat.ogasoft.protocolizer.messages.File.SearchRequest) obj;

      boolean result = true;
      result = result && (hasQuery() == other.hasQuery());
      if (hasQuery()) {
        result = result && getQuery()
            .equals(other.getQuery());
      }
      result = result && (hasPageNumber() == other.hasPageNumber());
      if (hasPageNumber()) {
        result = result && (getPageNumber()
            == other.getPageNumber());
      }
      result = result && (hasResultPerPage() == other.hasResultPerPage());
      if (hasResultPerPage()) {
        result = result && (getResultPerPage()
            == other.getResultPerPage());
      }
      result = result && (hasCorpussdfa() == other.hasCorpussdfa());
      if (hasCorpussdfa()) {
        result = result && corpussdfa_ == other.corpussdfa_;
      }
      result = result && unknownFields.equals(other.unknownFields);
      return result;
    }

    @java.lang.Override
    public int hashCode() {
      if (memoizedHashCode != 0) {
        return memoizedHashCode;
      }
      int hash = 41;
      hash = (19 * hash) + getDescriptor().hashCode();
      if (hasQuery()) {
        hash = (37 * hash) + QUERY_FIELD_NUMBER;
        hash = (53 * hash) + getQuery().hashCode();
      }
      if (hasPageNumber()) {
        hash = (37 * hash) + PAGE_NUMBER_FIELD_NUMBER;
        hash = (53 * hash) + getPageNumber();
      }
      if (hasResultPerPage()) {
        hash = (37 * hash) + RESULT_PER_PAGE_FIELD_NUMBER;
        hash = (53 * hash) + getResultPerPage();
      }
      if (hasCorpussdfa()) {
        hash = (37 * hash) + CORPUSSDFA_FIELD_NUMBER;
        hash = (53 * hash) + corpussdfa_;
      }
      hash = (29 * hash) + unknownFields.hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static cat.ogasoft.protocolizer.messages.File.SearchRequest parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static cat.ogasoft.protocolizer.messages.File.SearchRequest parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static cat.ogasoft.protocolizer.messages.File.SearchRequest parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static cat.ogasoft.protocolizer.messages.File.SearchRequest parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static cat.ogasoft.protocolizer.messages.File.SearchRequest parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static cat.ogasoft.protocolizer.messages.File.SearchRequest parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }
    public static cat.ogasoft.protocolizer.messages.File.SearchRequest parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }
    public static cat.ogasoft.protocolizer.messages.File.SearchRequest parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static cat.ogasoft.protocolizer.messages.File.SearchRequest parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static cat.ogasoft.protocolizer.messages.File.SearchRequest parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    public static Builder newBuilder(cat.ogasoft.protocolizer.messages.File.SearchRequest prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
    }
    public Builder toBuilder() {
      return this == DEFAULT_INSTANCE
          ? new Builder() : new Builder().mergeFrom(this);
    }

    @java.lang.Override
    protected Builder newBuilderForType(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    /**
     * Protobuf type {@code SearchRequest}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:SearchRequest)
        cat.ogasoft.protocolizer.messages.File.SearchRequestOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return cat.ogasoft.protocolizer.messages.File.internal_static_SearchRequest_descriptor;
      }

      protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return cat.ogasoft.protocolizer.messages.File.internal_static_SearchRequest_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                cat.ogasoft.protocolizer.messages.File.SearchRequest.class, cat.ogasoft.protocolizer.messages.File.SearchRequest.Builder.class);
      }

      // Construct using cat.ogasoft.protocolizer.messages.File.SearchRequest.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }

      private Builder(
          com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      private void maybeForceBuilderInitialization() {
        if (com.google.protobuf.GeneratedMessageV3
                .alwaysUseFieldBuilders) {
        }
      }
      public Builder clear() {
        super.clear();
        query_ = "";
        bitField0_ = (bitField0_ & ~0x00000001);
        pageNumber_ = 0;
        bitField0_ = (bitField0_ & ~0x00000002);
        resultPerPage_ = 0;
        bitField0_ = (bitField0_ & ~0x00000004);
        corpussdfa_ = 0;
        bitField0_ = (bitField0_ & ~0x00000008);
        return this;
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return cat.ogasoft.protocolizer.messages.File.internal_static_SearchRequest_descriptor;
      }

      public cat.ogasoft.protocolizer.messages.File.SearchRequest getDefaultInstanceForType() {
        return cat.ogasoft.protocolizer.messages.File.SearchRequest.getDefaultInstance();
      }

      public cat.ogasoft.protocolizer.messages.File.SearchRequest build() {
        cat.ogasoft.protocolizer.messages.File.SearchRequest result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public cat.ogasoft.protocolizer.messages.File.SearchRequest buildPartial() {
        cat.ogasoft.protocolizer.messages.File.SearchRequest result = new cat.ogasoft.protocolizer.messages.File.SearchRequest(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        result.query_ = query_;
        if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
          to_bitField0_ |= 0x00000002;
        }
        result.pageNumber_ = pageNumber_;
        if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
          to_bitField0_ |= 0x00000004;
        }
        result.resultPerPage_ = resultPerPage_;
        if (((from_bitField0_ & 0x00000008) == 0x00000008)) {
          to_bitField0_ |= 0x00000008;
        }
        result.corpussdfa_ = corpussdfa_;
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }

      public Builder clone() {
        return (Builder) super.clone();
      }
      public Builder setField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          Object value) {
        return (Builder) super.setField(field, value);
      }
      public Builder clearField(
          com.google.protobuf.Descriptors.FieldDescriptor field) {
        return (Builder) super.clearField(field);
      }
      public Builder clearOneof(
          com.google.protobuf.Descriptors.OneofDescriptor oneof) {
        return (Builder) super.clearOneof(oneof);
      }
      public Builder setRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          int index, Object value) {
        return (Builder) super.setRepeatedField(field, index, value);
      }
      public Builder addRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          Object value) {
        return (Builder) super.addRepeatedField(field, value);
      }
      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof cat.ogasoft.protocolizer.messages.File.SearchRequest) {
          return mergeFrom((cat.ogasoft.protocolizer.messages.File.SearchRequest)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(cat.ogasoft.protocolizer.messages.File.SearchRequest other) {
        if (other == cat.ogasoft.protocolizer.messages.File.SearchRequest.getDefaultInstance()) return this;
        if (other.hasQuery()) {
          bitField0_ |= 0x00000001;
          query_ = other.query_;
          onChanged();
        }
        if (other.hasPageNumber()) {
          setPageNumber(other.getPageNumber());
        }
        if (other.hasResultPerPage()) {
          setResultPerPage(other.getResultPerPage());
        }
        if (other.hasCorpussdfa()) {
          setCorpussdfa(other.getCorpussdfa());
        }
        this.mergeUnknownFields(other.unknownFields);
        onChanged();
        return this;
      }

      public final boolean isInitialized() {
        if (!hasQuery()) {
          return false;
        }
        if (!hasPageNumber()) {
          return false;
        }
        if (!hasResultPerPage()) {
          return false;
        }
        if (!hasCorpussdfa()) {
          return false;
        }
        return true;
      }

      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        cat.ogasoft.protocolizer.messages.File.SearchRequest parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (cat.ogasoft.protocolizer.messages.File.SearchRequest) e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      private java.lang.Object query_ = "";
      /**
       * <code>required string query = 1;</code>
       */
      public boolean hasQuery() {
        return ((bitField0_ & 0x00000001) == 0x00000001);
      }
      /**
       * <code>required string query = 1;</code>
       */
      public java.lang.String getQuery() {
        java.lang.Object ref = query_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          if (bs.isValidUtf8()) {
            query_ = s;
          }
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>required string query = 1;</code>
       */
      public com.google.protobuf.ByteString
          getQueryBytes() {
        java.lang.Object ref = query_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          query_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>required string query = 1;</code>
       */
      public Builder setQuery(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000001;
        query_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required string query = 1;</code>
       */
      public Builder clearQuery() {
        bitField0_ = (bitField0_ & ~0x00000001);
        query_ = getDefaultInstance().getQuery();
        onChanged();
        return this;
      }
      /**
       * <code>required string query = 1;</code>
       */
      public Builder setQueryBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000001;
        query_ = value;
        onChanged();
        return this;
      }

      private int pageNumber_ ;
      /**
       * <code>required int32 page_number = 2;</code>
       */
      public boolean hasPageNumber() {
        return ((bitField0_ & 0x00000002) == 0x00000002);
      }
      /**
       * <code>required int32 page_number = 2;</code>
       */
      public int getPageNumber() {
        return pageNumber_;
      }
      /**
       * <code>required int32 page_number = 2;</code>
       */
      public Builder setPageNumber(int value) {
        bitField0_ |= 0x00000002;
        pageNumber_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required int32 page_number = 2;</code>
       */
      public Builder clearPageNumber() {
        bitField0_ = (bitField0_ & ~0x00000002);
        pageNumber_ = 0;
        onChanged();
        return this;
      }

      private int resultPerPage_ ;
      /**
       * <code>required int32 result_per_page = 3;</code>
       */
      public boolean hasResultPerPage() {
        return ((bitField0_ & 0x00000004) == 0x00000004);
      }
      /**
       * <code>required int32 result_per_page = 3;</code>
       */
      public int getResultPerPage() {
        return resultPerPage_;
      }
      /**
       * <code>required int32 result_per_page = 3;</code>
       */
      public Builder setResultPerPage(int value) {
        bitField0_ |= 0x00000004;
        resultPerPage_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required int32 result_per_page = 3;</code>
       */
      public Builder clearResultPerPage() {
        bitField0_ = (bitField0_ & ~0x00000004);
        resultPerPage_ = 0;
        onChanged();
        return this;
      }

      private int corpussdfa_ = 0;
      /**
       * <code>required .SearchRequest.Corpus corpussdfa = 4;</code>
       */
      public boolean hasCorpussdfa() {
        return ((bitField0_ & 0x00000008) == 0x00000008);
      }
      /**
       * <code>required .SearchRequest.Corpus corpussdfa = 4;</code>
       */
      public cat.ogasoft.protocolizer.messages.File.SearchRequest.Corpus getCorpussdfa() {
        cat.ogasoft.protocolizer.messages.File.SearchRequest.Corpus result = cat.ogasoft.protocolizer.messages.File.SearchRequest.Corpus.valueOf(corpussdfa_);
        return result == null ? cat.ogasoft.protocolizer.messages.File.SearchRequest.Corpus.UNIVERSAL : result;
      }
      /**
       * <code>required .SearchRequest.Corpus corpussdfa = 4;</code>
       */
      public Builder setCorpussdfa(cat.ogasoft.protocolizer.messages.File.SearchRequest.Corpus value) {
        if (value == null) {
          throw new NullPointerException();
        }
        bitField0_ |= 0x00000008;
        corpussdfa_ = value.getNumber();
        onChanged();
        return this;
      }
      /**
       * <code>required .SearchRequest.Corpus corpussdfa = 4;</code>
       */
      public Builder clearCorpussdfa() {
        bitField0_ = (bitField0_ & ~0x00000008);
        corpussdfa_ = 0;
        onChanged();
        return this;
      }
      public final Builder setUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.setUnknownFields(unknownFields);
      }

      public final Builder mergeUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.mergeUnknownFields(unknownFields);
      }


      // @@protoc_insertion_point(builder_scope:SearchRequest)
    }

    // @@protoc_insertion_point(class_scope:SearchRequest)
    private static final cat.ogasoft.protocolizer.messages.File.SearchRequest DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new cat.ogasoft.protocolizer.messages.File.SearchRequest();
    }

    public static cat.ogasoft.protocolizer.messages.File.SearchRequest getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    @java.lang.Deprecated public static final com.google.protobuf.Parser<SearchRequest>
        PARSER = new com.google.protobuf.AbstractParser<SearchRequest>() {
      public SearchRequest parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
          return new SearchRequest(input, extensionRegistry);
      }
    };

    public static com.google.protobuf.Parser<SearchRequest> parser() {
      return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<SearchRequest> getParserForType() {
      return PARSER;
    }

    public cat.ogasoft.protocolizer.messages.File.SearchRequest getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_SearchRequest_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_SearchRequest_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n*cat/ogasoft/protocolizer/protoc/File.p" +
      "roto\"\323\001\n\rSearchRequest\022\r\n\005query\030\001 \002(\t\022\023\n" +
      "\013page_number\030\002 \002(\005\022\027\n\017result_per_page\030\003 " +
      "\002(\005\022)\n\ncorpussdfa\030\004 \002(\0162\025.SearchRequest." +
      "Corpus\"Z\n\006Corpus\022\r\n\tUNIVERSAL\020\000\022\007\n\003WEB\020\001" +
      "\022\n\n\006IMAGES\020\002\022\t\n\005LOCAL\020\003\022\010\n\004NEWS\020\004\022\014\n\010PRO" +
      "DUCTS\020\005\022\t\n\005VIDEO\020\006B)\n!cat.ogasoft.protoc" +
      "olizer.messagesB\004File"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
    internal_static_SearchRequest_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_SearchRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_SearchRequest_descriptor,
        new java.lang.String[] { "Query", "PageNumber", "ResultPerPage", "Corpussdfa", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}