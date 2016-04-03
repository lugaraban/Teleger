// This file is generated by omniidl (C++ backend)- omniORB_4_2. Do not edit.
#ifndef __teleger_hh__
#define __teleger_hh__

#ifndef __CORBA_H_EXTERNAL_GUARD__
#include <omniORB4/CORBA.h>
#endif

#ifndef  USE_stub_in_nt_dll
# define USE_stub_in_nt_dll_NOT_DEFINED_teleger
#endif
#ifndef  USE_core_stub_in_nt_dll
# define USE_core_stub_in_nt_dll_NOT_DEFINED_teleger
#endif
#ifndef  USE_dyn_stub_in_nt_dll
# define USE_dyn_stub_in_nt_dll_NOT_DEFINED_teleger
#endif






#ifdef USE_stub_in_nt_dll
# ifndef USE_core_stub_in_nt_dll
#  define USE_core_stub_in_nt_dll
# endif
# ifndef USE_dyn_stub_in_nt_dll
#  define USE_dyn_stub_in_nt_dll
# endif
#endif

#ifdef _core_attr
# error "A local CPP macro _core_attr has already been defined."
#else
# ifdef  USE_core_stub_in_nt_dll
#  define _core_attr _OMNIORB_NTDLL_IMPORT
# else
#  define _core_attr
# endif
#endif

#ifdef _dyn_attr
# error "A local CPP macro _dyn_attr has already been defined."
#else
# ifdef  USE_dyn_stub_in_nt_dll
#  define _dyn_attr _OMNIORB_NTDLL_IMPORT
# else
#  define _dyn_attr
# endif
#endif



_CORBA_MODULE teleger

_CORBA_MODULE_BEG

  struct User {
    typedef _CORBA_ConstrType_Variable_Var<User> _var_type;

    
    ::CORBA::String_member id;

    ::CORBA::String_member password;

    ::CORBA::String_member name;

    ::CORBA::String_member image;

  

    void operator>>= (cdrStream &) const;
    void operator<<= (cdrStream &);
  };

  typedef User::_var_type User_var;

  typedef _CORBA_ConstrType_Variable_OUT_arg< User,User_var > User_out;

  struct SafeUser {
    typedef _CORBA_ConstrType_Variable_Var<SafeUser> _var_type;

    
    ::CORBA::String_member id;

    ::CORBA::String_member name;

    ::CORBA::String_member image;

    ::CORBA::String_member ip;

  

    void operator>>= (cdrStream &) const;
    void operator<<= (cdrStream &);
  };

  typedef SafeUser::_var_type SafeUser_var;

  typedef _CORBA_ConstrType_Variable_OUT_arg< SafeUser,SafeUser_var > SafeUser_out;

  class userFriends_var;

  class userFriends : public _CORBA_Unbounded_Sequence< SafeUser >  {
  public:
    typedef userFriends_var _var_type;
    inline userFriends() {}
    inline userFriends(const userFriends& _s)
      : _CORBA_Unbounded_Sequence< SafeUser > (_s) {}

    inline userFriends(_CORBA_ULong _max)
      : _CORBA_Unbounded_Sequence< SafeUser > (_max) {}
    inline userFriends(_CORBA_ULong _max, _CORBA_ULong _len, SafeUser* _val, _CORBA_Boolean _rel=0)
      : _CORBA_Unbounded_Sequence< SafeUser > (_max, _len, _val, _rel) {}

  

    inline userFriends& operator = (const userFriends& _s) {
      _CORBA_Unbounded_Sequence< SafeUser > ::operator=(_s);
      return *this;
    }
  };

  class userFriends_out;

  class userFriends_var {
  public:
    inline userFriends_var() : _pd_seq(0) {}
    inline userFriends_var(userFriends* _s) : _pd_seq(_s) {}
    inline userFriends_var(const userFriends_var& _s) {
      if (_s._pd_seq)  _pd_seq = new userFriends(*_s._pd_seq);
      else             _pd_seq = 0;
    }
    inline ~userFriends_var() { if (_pd_seq)  delete _pd_seq; }
      
    inline userFriends_var& operator = (userFriends* _s) {
      if (_pd_seq)  delete _pd_seq;
      _pd_seq = _s;
      return *this;
    }
    inline userFriends_var& operator = (const userFriends_var& _s) {
      if (&_s != this) {
        if (_s._pd_seq) {
          if (!_pd_seq)  _pd_seq = new userFriends;
          *_pd_seq = *_s._pd_seq;
        }
        else if (_pd_seq) {
          delete _pd_seq;
          _pd_seq = 0;
        }
      }
      return *this;
    }
    inline SafeUser& operator [] (_CORBA_ULong _s) {
      return (*_pd_seq)[_s];
    }

  

    inline userFriends* operator -> () { return _pd_seq; }
    inline const userFriends* operator -> () const { return _pd_seq; }
#if defined(__GNUG__)
    inline operator userFriends& () const { return *_pd_seq; }
#else
    inline operator const userFriends& () const { return *_pd_seq; }
    inline operator userFriends& () { return *_pd_seq; }
#endif
      
    inline const userFriends& in() const { return *_pd_seq; }
    inline userFriends&       inout()    { return *_pd_seq; }
    inline userFriends*&      out() {
      if (_pd_seq) { delete _pd_seq; _pd_seq = 0; }
      return _pd_seq;
    }
    inline userFriends* _retn() { userFriends* tmp = _pd_seq; _pd_seq = 0; return tmp; }
      
    friend class userFriends_out;
    
  private:
    userFriends* _pd_seq;
  };

  class userFriends_out {
  public:
    inline userFriends_out(userFriends*& _s) : _data(_s) { _data = 0; }
    inline userFriends_out(userFriends_var& _s)
      : _data(_s._pd_seq) { _s = (userFriends*) 0; }
    inline userFriends_out(const userFriends_out& _s) : _data(_s._data) {}
    inline userFriends_out& operator = (const userFriends_out& _s) {
      _data = _s._data;
      return *this;
    }
    inline userFriends_out& operator = (userFriends* _s) {
      _data = _s;
      return *this;
    }
    inline operator userFriends*&()  { return _data; }
    inline userFriends*& ptr()       { return _data; }
    inline userFriends* operator->() { return _data; }

    inline SafeUser& operator [] (_CORBA_ULong _i) {
      return (*_data)[_i];
    }

  

    userFriends*& _data;

  private:
    userFriends_out();
    userFriends_out& operator=(const userFriends_var&);
  };

#ifndef __teleger_mClientInterface__
#define __teleger_mClientInterface__
  class ClientInterface;
  class _objref_ClientInterface;
  class _impl_ClientInterface;
  
  typedef _objref_ClientInterface* ClientInterface_ptr;
  typedef ClientInterface_ptr ClientInterfaceRef;

  class ClientInterface_Helper {
  public:
    typedef ClientInterface_ptr _ptr_type;

    static _ptr_type _nil();
    static _CORBA_Boolean is_nil(_ptr_type);
    static void release(_ptr_type);
    static void duplicate(_ptr_type);
    static void marshalObjRef(_ptr_type, cdrStream&);
    static _ptr_type unmarshalObjRef(cdrStream&);
  };

  typedef _CORBA_ObjRef_Var<_objref_ClientInterface, ClientInterface_Helper> ClientInterface_var;
  typedef _CORBA_ObjRef_OUT_arg<_objref_ClientInterface,ClientInterface_Helper > ClientInterface_out;

#endif

  // interface ClientInterface
  class ClientInterface {
  public:
    // Declarations for this interface type.
    typedef ClientInterface_ptr _ptr_type;
    typedef ClientInterface_var _var_type;

    static _ptr_type _duplicate(_ptr_type);
    static _ptr_type _narrow(::CORBA::Object_ptr);
    static _ptr_type _unchecked_narrow(::CORBA::Object_ptr);
    
    static _ptr_type _nil();

    static inline void _marshalObjRef(_ptr_type, cdrStream&);

    static inline _ptr_type _unmarshalObjRef(cdrStream& s) {
      omniObjRef* o = omniObjRef::_unMarshal(_PD_repoId,s);
      if (o)
        return (_ptr_type) o->_ptrToObjRef(_PD_repoId);
      else
        return _nil();
    }

    static inline _ptr_type _fromObjRef(omniObjRef* o) {
      if (o)
        return (_ptr_type) o->_ptrToObjRef(_PD_repoId);
      else
        return _nil();
    }

    static _core_attr const char* _PD_repoId;

    // Other IDL defined within this scope.
    
  };

  class _objref_ClientInterface :
    public virtual ::CORBA::Object,
    public virtual omniObjRef
  {
  public:
    // IDL operations
    void notifyConnection(const ::teleger::SafeUser& connectedUser);
    ::CORBA::Boolean receiveFriendRequest(const ::teleger::SafeUser& user);
    void notifyAnswerRequest(const ::teleger::SafeUser& connectedUser, ::CORBA::Boolean acceptance);

    // Constructors
    inline _objref_ClientInterface()  { _PR_setobj(0); }  // nil
    _objref_ClientInterface(omniIOR*, omniIdentity*);

  protected:
    virtual ~_objref_ClientInterface();

    
  private:
    virtual void* _ptrToObjRef(const char*);

    _objref_ClientInterface(const _objref_ClientInterface&);
    _objref_ClientInterface& operator = (const _objref_ClientInterface&);
    // not implemented

    friend class ClientInterface;
  };

  class _pof_ClientInterface : public _OMNI_NS(proxyObjectFactory) {
  public:
    inline _pof_ClientInterface() : _OMNI_NS(proxyObjectFactory)(ClientInterface::_PD_repoId) {}
    virtual ~_pof_ClientInterface();

    virtual omniObjRef* newObjRef(omniIOR*,omniIdentity*);
    virtual _CORBA_Boolean is_a(const char*) const;
  };

  class _impl_ClientInterface :
    public virtual omniServant
  {
  public:
    virtual ~_impl_ClientInterface();

    virtual void notifyConnection(const ::teleger::SafeUser& connectedUser) = 0;
    virtual ::CORBA::Boolean receiveFriendRequest(const ::teleger::SafeUser& user) = 0;
    virtual void notifyAnswerRequest(const ::teleger::SafeUser& connectedUser, ::CORBA::Boolean acceptance) = 0;
    
  public:  // Really protected, workaround for xlC
    virtual _CORBA_Boolean _dispatch(omniCallHandle&);

  private:
    virtual void* _ptrToInterface(const char*);
    virtual const char* _mostDerivedRepoId();
    
  };


#ifndef __teleger_mServerInterface__
#define __teleger_mServerInterface__
  class ServerInterface;
  class _objref_ServerInterface;
  class _impl_ServerInterface;
  
  typedef _objref_ServerInterface* ServerInterface_ptr;
  typedef ServerInterface_ptr ServerInterfaceRef;

  class ServerInterface_Helper {
  public:
    typedef ServerInterface_ptr _ptr_type;

    static _ptr_type _nil();
    static _CORBA_Boolean is_nil(_ptr_type);
    static void release(_ptr_type);
    static void duplicate(_ptr_type);
    static void marshalObjRef(_ptr_type, cdrStream&);
    static _ptr_type unmarshalObjRef(cdrStream&);
  };

  typedef _CORBA_ObjRef_Var<_objref_ServerInterface, ServerInterface_Helper> ServerInterface_var;
  typedef _CORBA_ObjRef_OUT_arg<_objref_ServerInterface,ServerInterface_Helper > ServerInterface_out;

#endif

  // interface ServerInterface
  class ServerInterface {
  public:
    // Declarations for this interface type.
    typedef ServerInterface_ptr _ptr_type;
    typedef ServerInterface_var _var_type;

    static _ptr_type _duplicate(_ptr_type);
    static _ptr_type _narrow(::CORBA::Object_ptr);
    static _ptr_type _unchecked_narrow(::CORBA::Object_ptr);
    
    static _ptr_type _nil();

    static inline void _marshalObjRef(_ptr_type, cdrStream&);

    static inline _ptr_type _unmarshalObjRef(cdrStream& s) {
      omniObjRef* o = omniObjRef::_unMarshal(_PD_repoId,s);
      if (o)
        return (_ptr_type) o->_ptrToObjRef(_PD_repoId);
      else
        return _nil();
    }

    static inline _ptr_type _fromObjRef(omniObjRef* o) {
      if (o)
        return (_ptr_type) o->_ptrToObjRef(_PD_repoId);
      else
        return _nil();
    }

    static _core_attr const char* _PD_repoId;

    // Other IDL defined within this scope.
    
  };

  class _objref_ServerInterface :
    public virtual ::CORBA::Object,
    public virtual omniObjRef
  {
  public:
    // IDL operations
    ::CORBA::Boolean _cxx_register(const ::teleger::User& userData);
    userFriends* logIn(const char* userId, const char* userPassword, const char* ip, ::teleger::ClientInterface_ptr client);
    ::CORBA::Boolean logOut(const char* userId, const char* userPassword);
    userFriends* searchNewFriends(const char* name);
    void sendRequestForFriend(const ::teleger::SafeUser& user, const ::teleger::SafeUser& _cxx_friend);

    // Constructors
    inline _objref_ServerInterface()  { _PR_setobj(0); }  // nil
    _objref_ServerInterface(omniIOR*, omniIdentity*);

  protected:
    virtual ~_objref_ServerInterface();

    
  private:
    virtual void* _ptrToObjRef(const char*);

    _objref_ServerInterface(const _objref_ServerInterface&);
    _objref_ServerInterface& operator = (const _objref_ServerInterface&);
    // not implemented

    friend class ServerInterface;
  };

  class _pof_ServerInterface : public _OMNI_NS(proxyObjectFactory) {
  public:
    inline _pof_ServerInterface() : _OMNI_NS(proxyObjectFactory)(ServerInterface::_PD_repoId) {}
    virtual ~_pof_ServerInterface();

    virtual omniObjRef* newObjRef(omniIOR*,omniIdentity*);
    virtual _CORBA_Boolean is_a(const char*) const;
  };

  class _impl_ServerInterface :
    public virtual omniServant
  {
  public:
    virtual ~_impl_ServerInterface();

    virtual ::CORBA::Boolean _cxx_register(const ::teleger::User& userData) = 0;
    virtual userFriends* logIn(const char* userId, const char* userPassword, const char* ip, ::teleger::ClientInterface_ptr client) = 0;
    virtual ::CORBA::Boolean logOut(const char* userId, const char* userPassword) = 0;
    virtual userFriends* searchNewFriends(const char* name) = 0;
    virtual void sendRequestForFriend(const ::teleger::SafeUser& user, const ::teleger::SafeUser& _cxx_friend) = 0;
    
  public:  // Really protected, workaround for xlC
    virtual _CORBA_Boolean _dispatch(omniCallHandle&);

  private:
    virtual void* _ptrToInterface(const char*);
    virtual const char* _mostDerivedRepoId();
    
  };


_CORBA_MODULE_END



_CORBA_MODULE POA_teleger
_CORBA_MODULE_BEG

  class ClientInterface :
    public virtual teleger::_impl_ClientInterface,
    public virtual ::PortableServer::ServantBase
  {
  public:
    virtual ~ClientInterface();

    inline ::teleger::ClientInterface_ptr _this() {
      return (::teleger::ClientInterface_ptr) _do_this(::teleger::ClientInterface::_PD_repoId);
    }
  };

  class ServerInterface :
    public virtual teleger::_impl_ServerInterface,
    public virtual ::PortableServer::ServantBase
  {
  public:
    virtual ~ServerInterface();

    inline ::teleger::ServerInterface_ptr _this() {
      return (::teleger::ServerInterface_ptr) _do_this(::teleger::ServerInterface::_PD_repoId);
    }
  };

_CORBA_MODULE_END



_CORBA_MODULE OBV_teleger
_CORBA_MODULE_BEG

_CORBA_MODULE_END





#undef _core_attr
#undef _dyn_attr



inline void
teleger::ClientInterface::_marshalObjRef(::teleger::ClientInterface_ptr obj, cdrStream& s) {
  omniObjRef::_marshal(obj->_PR_getobj(),s);
}

inline void
teleger::ServerInterface::_marshalObjRef(::teleger::ServerInterface_ptr obj, cdrStream& s) {
  omniObjRef::_marshal(obj->_PR_getobj(),s);
}



#ifdef   USE_stub_in_nt_dll_NOT_DEFINED_teleger
# undef  USE_stub_in_nt_dll
# undef  USE_stub_in_nt_dll_NOT_DEFINED_teleger
#endif
#ifdef   USE_core_stub_in_nt_dll_NOT_DEFINED_teleger
# undef  USE_core_stub_in_nt_dll
# undef  USE_core_stub_in_nt_dll_NOT_DEFINED_teleger
#endif
#ifdef   USE_dyn_stub_in_nt_dll_NOT_DEFINED_teleger
# undef  USE_dyn_stub_in_nt_dll
# undef  USE_dyn_stub_in_nt_dll_NOT_DEFINED_teleger
#endif

#endif  // __teleger_hh__

